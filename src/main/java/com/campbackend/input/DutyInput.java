package com.campbackend.input;

import java.util.UUID;

import com.campbackend.modal.Duty;

import lombok.Getter;
import lombok.Setter;

public class DutyInput extends Duty{
@Getter @Setter
private UUID churchId;
@Getter @Setter
private UUID accountHolderId;
}
