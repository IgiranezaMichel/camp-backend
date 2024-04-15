package com.campbackend.input;

import com.campbackend.modal.CampApplicant;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

public class CampApplicantInput extends CampApplicant{
@Getter @Setter private UUID campId;
@Getter @Setter private UUID accountHolderId;

}
