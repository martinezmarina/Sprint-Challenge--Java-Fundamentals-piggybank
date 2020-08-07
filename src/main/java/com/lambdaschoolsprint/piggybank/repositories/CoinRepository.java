package com.lambdaschoolsprint.piggybank.repositories;

import com.lambdaschoolsprint.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long> {
}
