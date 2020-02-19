package com.papi.ohadasys.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A NstBalanceInput.
 */
@Document(collection = "nst_balance_input")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "nstbalanceinput")
public class NstBalanceInput implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("num_compte")
    private String numCompte;

    @NotNull
    @Field("intitul_compte")
    private String intitulCompte;

    @Field("solde_debit")
    private Long soldeDebit;

    @Field("solde_credit")
    private Long soldeCredit;

    @Field("compte_number")
    private String compteNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public NstBalanceInput numCompte(String numCompte) {
        this.numCompte = numCompte;
        return this;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getIntitulCompte() {
        return intitulCompte;
    }

    public NstBalanceInput intitulCompte(String intitulCompte) {
        this.intitulCompte = intitulCompte;
        return this;
    }

    public void setIntitulCompte(String intitulCompte) {
        this.intitulCompte = intitulCompte;
    }

    public Long getSoldeDebit() {
        return soldeDebit;
    }

    public NstBalanceInput soldeDebit(Long soldeDebit) {
        this.soldeDebit = soldeDebit;
        return this;
    }

    public void setSoldeDebit(Long soldeDebit) {
        this.soldeDebit = soldeDebit;
    }

    public Long getSoldeCredit() {
        return soldeCredit;
    }

    public NstBalanceInput soldeCredit(Long soldeCredit) {
        this.soldeCredit = soldeCredit;
        return this;
    }

    public void setSoldeCredit(Long soldeCredit) {
        this.soldeCredit = soldeCredit;
    }

    public String getCompteNumber() {
        return compteNumber;
    }

    public NstBalanceInput compteNumber(String compteNumber) {
        this.compteNumber = compteNumber;
        return this;
    }

    public void setCompteNumber(String compteNumber) {
        this.compteNumber = compteNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NstBalanceInput)) {
            return false;
        }
        return id != null && id.equals(((NstBalanceInput) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NstBalanceInput{" +
            "id=" + getId() +
            ", numCompte='" + getNumCompte() + "'" +
            ", intitulCompte='" + getIntitulCompte() + "'" +
            ", soldeDebit=" + getSoldeDebit() +
            ", soldeCredit=" + getSoldeCredit() +
            ", compteNumber='" + getCompteNumber() + "'" +
            "}";
    }
}
