package com.tamagotchipokemon.repositories;

import com.tamagotchipokemon.models.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttackRepository extends JpaRepository<Attack, Long> {
    Attack getAttackById(Long id);
}
