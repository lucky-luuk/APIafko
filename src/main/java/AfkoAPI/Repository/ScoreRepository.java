package AfkoAPI.Repository;

import AfkoAPI.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository  extends JpaRepository<Player, Integer>{

    List<Player> findTop5ByOrderByScoreDesc();
}
