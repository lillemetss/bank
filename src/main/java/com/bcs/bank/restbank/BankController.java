package com.bcs.bank.restbank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/solution")
public class BankController {

    public static Bank bank = new Bank(); // static tähendab seda, see on jagatud koht?  et millal tehakse Bank muutuja seda me ei korda ja tehakse ühe korra.

    // bank on classi muutuja
    @Resource
    private AccountService accountService; // samal tasamel kui meetodi aga pole meetodi sees.

    @Resource
    private TransactionService transactionService;

    @Resource
    private BankService bankService;

    @GetMapping("/bank")
    public Bank getBank() {
        return bank;
    }

    @GetMapping("/example/account")
    public AccountDto getExampleAccount() {
        return accountService.createExampleAccount();
    }

    @GetMapping("/example/transaction")
    public TransactionDto getExampleTransaction() {
        return transactionService.createExampleTransaction();
    }

    @PostMapping("/new/account")
    public RequestResult addAccountToBank(@RequestBody AccountDto accountDto) {
        return bankService.addAccountToBank(bank, accountDto);
    }

    @PostMapping("/new/transaction")
    public RequestResult addNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.addNewTransaction(bank, transactionDto);
    }

    @PostMapping("/receive/transaction")
    public RequestResult receiveNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.ReceiveNewTransaction(bank, transactionDto);
    }

    @PutMapping("/update/owner")
    public RequestResult updateOwnerDetails(@RequestBody AccountDto accountDto) {
        return accountService.updateOwnerDetails(bank.getAccounts(), accountDto);
    }

    // TODO Lock account / make sure wrong account id is handled| tehke endpoint millega saab kontot lukustada ja avada ja kontollige ID olemasolu
    // lock/status
    @PutMapping("/update/locked")
    public RequestResult updateLockedStatus(@RequestBody AccountDto accountDto) { // võib olla ka @RequestParam int accountId
        return accountService.updateLockedStatus(bank.getAccounts(), accountDto);
    }

    @DeleteMapping("/delete/account")
    public RequestResult deleteAccount(@RequestParam int accountId) {
        return accountService.deleteAccount(bank.getAccounts(), accountId);
    }
}

    //  loo transactionService alla uus teenus                                      createTransactionForNewAccount()
    //  loo bankService alla uus teenus                                             addTransaction()


