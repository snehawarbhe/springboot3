package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int custId;
    @Size(min = 2,message = "Customer Name should be atleast 2 Character")
    private String custName;

    @NotNull
    private String custAddress;

    @Column(unique = true)
    @Range(min = 1000000000,max = 9999999999L,message = "Customer ContactNumber must be 10 digit")
    private long custContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    @Column(unique = true)
    @Email(message = "EmailId must be valid")
    private String custEmailId;

    @Size(min = 4,message = "Customer Password should be atleast 4 character")
    private String custPassword;


}
