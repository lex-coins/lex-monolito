package br.com.lexcoins.service;

import static org.junit.Assert.assertEquals;

import br.com.lexcoins.model.Wallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;


    @Test
    public void findAll(){

    }
    @Test
    public void findById(){
        Wallet wallet = walletService.findById(1L);
        assertEquals("",wallet.getCryptos().getName());
        assertEquals("",String.valueOf(wallet.getAmount()));
        assertEquals("",wallet.getPrivateKey());
        assertEquals("",wallet.getPublicKey());
        assertEquals("",wallet.getPerson().getName());
    }

    @Test
    public void saveWallet(){

    }

    @Test
    public void findByPersonId(){

    }


}
