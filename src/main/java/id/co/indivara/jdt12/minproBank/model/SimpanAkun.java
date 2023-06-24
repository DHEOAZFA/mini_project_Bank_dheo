package id.co.indivara.jdt12.minproBank.model;

import lombok.Data;

@Data
public class SimpanAkun {
    private String akunId;
    private String idPelanggan;
    private Integer noRekening;
    private Integer pin;
}

