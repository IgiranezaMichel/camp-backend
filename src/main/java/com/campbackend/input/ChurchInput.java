package com.campbackend.input;

import java.util.UUID;

import com.campbackend.modal.Church;

import lombok.Getter;
import lombok.Setter;

public class ChurchInput extends Church{
@Getter @Setter
private UUID churchId;
}
