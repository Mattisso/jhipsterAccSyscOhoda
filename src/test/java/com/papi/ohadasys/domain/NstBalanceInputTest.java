package com.papi.ohadasys.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.papi.ohadasys.web.rest.TestUtil;

public class NstBalanceInputTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NstBalanceInput.class);
        NstBalanceInput nstBalanceInput1 = new NstBalanceInput();
        nstBalanceInput1.setId("id1");
        NstBalanceInput nstBalanceInput2 = new NstBalanceInput();
        nstBalanceInput2.setId(nstBalanceInput1.getId());
        assertThat(nstBalanceInput1).isEqualTo(nstBalanceInput2);
        nstBalanceInput2.setId("id2");
        assertThat(nstBalanceInput1).isNotEqualTo(nstBalanceInput2);
        nstBalanceInput1.setId(null);
        assertThat(nstBalanceInput1).isNotEqualTo(nstBalanceInput2);
    }
}
