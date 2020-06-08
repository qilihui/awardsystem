package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.model.dto.*;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserStuService;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Service
@Slf4j
public class UserStuServiceImpl implements UserStuService {
    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    UserUnionDao userUnionDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHouseparentDao userHouseparentDao;

    @Autowired
    private UserTutorDao userTutorDao;


//    @Override
//    public List<SysUserStu> findAll() {
//        return userStuDao.findAll();
//    }

//    @Override
//    public Optional<SysUserStu> findById(Integer id) {
//        return userStuDao.findById(id);
//    }

    @Override
    @Transactional
    public Integer save(UserInfoDto userInfoDto, SysUserDto sysUserDto) throws EntityFieldException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername().trim());
        sysUser.setPassword(sysUserDto.getUsername());
        sysUser.setEmail(sysUserDto.getEmail().trim());
        sysUser.setRealName(sysUserDto.getRealName().trim());
        sysUser.setRole(RoleEnum.ROLE_STU);

        SysUserStu userStu = new SysUserStu();
        deptDao.findById(userInfoDto.getDeptId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userInfoDto.getDeptId() + "不存在")
        );
        userStu.setDeptId(userInfoDto.getDeptId());
        gradeDao.findById(userInfoDto.getGradeId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userInfoDto.getGradeId() + "不存在")
        );
        userStu.setGradeId(userInfoDto.getGradeId());
        apartmentDao.findById(userInfoDto.getApartmentId()).orElseThrow(
                () -> new EntityFieldException("公寓id:" + userInfoDto.getApartmentId() + "不存在")
        );
        userStu.setApartmentId(userInfoDto.getApartmentId());
        userStu.setRoom(userInfoDto.getRoom());
        userStu.setBed(userInfoDto.getBed());
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        userStu.setUser(sysUser);
        return userStuDao.save(userStu);
    }

    @Override
    @Transactional
    public Integer saves(StuDto[] stuDtos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < stuDtos.length; i++) {
            try {
                log.info(stuDtos[i].toString());
                Optional<SysDept> deptOptional = deptDao.findSysDeptByName(stuDtos[i].getDeptName());
                Optional<SysGrade> gradeOptional = gradeDao.findSysGradeByName(Integer.valueOf(stuDtos[i].getGradeName().trim()));
                Optional<SysApartment> apartmentOptional = apartmentDao.findSysApartmentByName(stuDtos[i].getApartmentName());
                SysDept dept = deptOptional.orElseThrow(() -> {
                            return new EntityFieldException("系部不存在");
                        }
                );
                SysGrade grade = gradeOptional.orElseThrow(() -> {
                            return new EntityFieldException("年级不存在");
                        }
                );
                SysApartment apartment = apartmentOptional.orElseThrow(() -> {
                            return new EntityFieldException("公寓不存在");
                        }
                );
                SysUser sysUser = new SysUser();
                sysUser.setUsername(stuDtos[i].getUsername().trim());
                sysUser.setRole(RoleEnum.ROLE_STU);
                sysUser.setEmail(stuDtos[i].getEmail().trim());
                sysUser.setRealName(stuDtos[i].getRealName().trim());
                if (userService.save(sysUser) <= 0) {
                    throw new EntityFieldException("未知错误");
                }
                SysUserStu userStu = new SysUserStu();
                userStu.setUser(sysUser);
                userStu.setDeptId(dept.getId());
                userStu.setGradeId(grade.getId());
                userStu.setApartmentId(apartment.getId());
                userStu.setRoom(Integer.valueOf(stuDtos[i].getRoom().trim()));
                userStu.setBed(Integer.valueOf(stuDtos[i].getBed().trim()));
                j += userStuDao.save(userStu);
            } catch (EntityFieldException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " " + e.getMessage());
            } catch (NumberFormatException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " 格式错误" + e.getMessage());
            }
        }
        return j;
    }

    @Override
    public Optional<SysUserStu> findBySysUserId(Integer id) {
        return userStuDao.findSysUserStuByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_STU.toString())) {
            throw new EntityFieldException("角色错误");
        }
        if (userInfoDto.getUserInfoId() == null) {
            throw new EntityFieldException("缺少userInfoId字段");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userStuDao.updateInfo(userInfoDto);
        return integer + integer1;
    }

    @Override
    @RolesAllowed("UNION")
    public SysUserStu findSysUserStuByUsername(String username) throws EntityFieldException {
        Optional<SysUserStu> sysUserOptional = userStuDao.findStuByUsername(username);
        SysUserStu userStu = sysUserOptional.orElseThrow(() -> new EntityFieldException("该用户名学生不存在"));
        Optional<SysUserUnion> userUnionOptional = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId());
        if (!userUnionOptional.get().getDeptId().equals(userStu.getDeptId())) {
            throw new EntityFieldException("不是本系学生");
        }
        return sysUserOptional.get();
    }

    @Override
    @RolesAllowed("ADMIN")
    public SysUserStu findSysUserStuByUsernameToAdmin(String username) throws EntityFieldException {
        return userStuDao.findStuByUsername(username).orElseThrow(() -> new EntityFieldException("该用户名学生不存在"));
    }

    @Override
    @RolesAllowed("HOUSEPARENT")
    @Transactional
    public PageDto<List<StuDto>> findByHouseparent(Integer pageNum, Integer pageSize) throws UnknownException {
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        Integer apartmentId = userHouseparentOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员")).getApartmentId();
        List<SysUserStu> sysUserStus = userStuDao.findByApartmentId(apartmentId, pageNum, pageSize);
        ArrayList<StuDto> stuDtos = new ArrayList<>();
        for (SysUserStu userStu : sysUserStus) {
            SysUser sysUser = userStu.getUser();
            String deptName = deptDao.findById(userStu.getDeptId()).orElseThrow(() -> new UnknownException("未知错误 请联系管理员")).getName();
            StuDto stuDto = new StuDto(sysUser.getUsername(), sysUser.getRealName(), sysUser.getEmail(), userStu.getRoom().toString(), userStu.getBed().toString(), null, deptName, null);
            stuDtos.add(stuDto);
        }
        Integer count = userStuDao.findCountByApartmentId(apartmentId);
        PageDto<List<StuDto>> pageDto = new PageDto<>();
        pageDto.setCount(count);
        pageDto.setObj(stuDtos);
        return pageDto;
    }

    @Override
    public List<ApartmentScoreDto> findByHouseparentToExcel() throws UnknownException {
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        Integer apartmentId = userHouseparentOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员")).getApartmentId();
        List<SysUserStu> userStuList = userStuDao.findByApartmentIdToExcel(apartmentId);
        List<ApartmentScoreDto> scoreDtoList = new ArrayList<>();
        Map<Integer, ApartmentScoreDto> map = new HashMap<>();
        for (SysUserStu s : userStuList) {
            ApartmentScoreDto tmp = map.get(s.getRoom());
            if (tmp != null) {
                tmp.setS(s.getBed(), "2");
            } else {
                tmp = new ApartmentScoreDto();
                tmp.setRoom(s.getRoom().toString());
                tmp.setS(s.getBed(), "2");
                map.put(s.getRoom(), tmp);
                scoreDtoList.add(tmp);
            }
        }
        return scoreDtoList;
    }

    @Override
    @RolesAllowed("TUTOR")
    @Transactional
    public PageDto<List<StuDto>> findByTutor(Integer pageNum, Integer pageSize) throws UnknownException {
        Optional<SysUserTutor> tutorOptional = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId());
        SysUserTutor userTutor = tutorOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员"));
        List<SysUserStu> sysUserStus = userStuDao.findByDeptIdAndGradeId(userTutor.getDeptId(), userTutor.getGradeId(), pageNum, pageSize);
        ArrayList<StuDto> stuDtos = new ArrayList<>();
        for (SysUserStu userStu : sysUserStus) {
            SysUser sysUser = userStu.getUser();
            String apartmentName = apartmentDao.findById(userStu.getApartmentId()).orElseThrow(() -> new UnknownException("未知错误 请联系管理员")).getName();
            StuDto stuDto = new StuDto(sysUser.getUsername(), sysUser.getRealName(), sysUser.getEmail(), userStu.getRoom().toString(), userStu.getBed().toString(), apartmentName, null, null);
            stuDtos.add(stuDto);
        }
        Integer count = userStuDao.findCountByDeptIdAndGradeId(userTutor.getDeptId(), userTutor.getGradeId());
        PageDto<List<StuDto>> pageDto = new PageDto<>();
        pageDto.setCount(count);
        pageDto.setObj(stuDtos);
        return pageDto;
    }
}
