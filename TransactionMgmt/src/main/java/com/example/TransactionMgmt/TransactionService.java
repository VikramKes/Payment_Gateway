package com.example.TransactionMgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    com.example.TransactionMgmt.TransactionRepository transactionRepository;
    @Autowired
    KafkaTemplate kafkaTemplate;
    public void create(TransactionRequest transactionRequest){
        com.example.TransactionMgmt.Transaction transaction = new com.example.TransactionMgmt.Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setMode(transactionRequest.getMode());
        transaction.setStatus(Status.SUCCESS.toString());
        transaction.setType(transactionRequest.getType());
        transaction.setUsername(transactionRequest.getUsername());

        com.example.TransactionMgmt.PaymentDetails paymentDetails = new com.example.TransactionMgmt.PaymentDetails();
        paymentDetails.setBankId(transactionRequest.getBankId());
        paymentDetails.setSource(transactionRequest.getSource());
        paymentDetails.setTransactionId(transactionRequest.getTransactionId());

        transaction.setPaymentDetails(paymentDetails);

        transactionRepository.save(transaction);
        com.example.TransactionMgmt.Event event = new com.example.TransactionMgmt.Event();
        event.setName(transaction.getMode()+"TRANSACTION"+transaction.getType());
        event.setUser(transaction.getUsername());
        event.setData(String.valueOf(transaction.getAmount()));
        kafkaTemplate.send("TRANSACTION", event);
    }



}
