package com.synthilearn.customerservice.infra.persistence.jpa.entity;

import com.synthilearn.customerservice.domain.RegisterStatus;
import com.synthilearn.customerservice.domain.RegistrationType;
import com.synthilearn.customerservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "customer")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    private String name;
    private String surname;
    @Column("birth_date")
    private LocalDate birthDate;
    private String email;
    @Column("registration_type")
    private RegistrationType registrationType;
    @Column("creation_date")
    private ZonedDateTime creationDate;
    @Column("last_updated_date")
    private ZonedDateTime updatedDate;
    private RegisterStatus status;
    private Role role;
    @Transient
    private boolean newRecord;

    @Override
    public boolean isNew() {
        return this.newRecord;
    }
}

