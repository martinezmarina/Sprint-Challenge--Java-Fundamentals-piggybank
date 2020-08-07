package com.lambdaschoolsprint.piggybank.controllers;

import com.lambdaschoolsprint.piggybank.models.Coin;
import com.lambdaschoolsprint.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    CoinRepository coinrepos;
    private List<Coin> findCoins(List<Coin> coinList, CheckCoin tester){
        List<Coin> tempList = new ArrayList<>();
        for(Coin c : coinList){
            if(tester.test(c)){
                tempList.add(c);
            }
        }
        return tempList;
    }
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> getCoinTotal(){
        List<Coin> coinList = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(coinList::add);
        double coinTotal = 0;
        for(Coin c : coinList){
            coinTotal = (c.getQuantity() * c.getValue())+coinTotal;
            int quantity = c.getQuantity();
            String coinName = null;
            if(quantity == 1){
                coinName = c.getName();
            } else if(quantity > 1){
                coinName = c.getNameplural();
            }
            System.out.println(quantity + " " + coinName);

        }
        System.out.println("The piggy bank holds " + coinTotal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
