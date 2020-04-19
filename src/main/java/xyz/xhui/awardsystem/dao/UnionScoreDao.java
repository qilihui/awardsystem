package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.UnionScore;

public interface UnionScoreDao extends JpaRepository<UnionScore, Integer> {
}
