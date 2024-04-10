package com.campbackend.input;

import com.campbackend.modal.AccountHolder;

import lombok.Getter;
import lombok.Setter;

public class AccountHolderInput extends AccountHolder{
    @Getter @Setter
    private String base64Profile;
}
