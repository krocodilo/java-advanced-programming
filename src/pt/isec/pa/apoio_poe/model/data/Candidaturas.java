package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Candidaturas implements Serializable {

    private static final long SerialVersionUID = 1L;

    private long idAluno;
    private ArrayList<String> idsPropostas;

    Proposta proposta;

    public Candidaturas(long idAluno, List<String> idsPropostas) {
        this.idAluno = idAluno;
        this.idsPropostas = new ArrayList<>( idsPropostas );
    }

    public long getIdAluno() {
        return idAluno;
    }

    public List<String> getIdsPropostas() {
        return idsPropostas;
    }

    public List<Integer> getHashCodePropostas(){
        ArrayList<Integer> hashcodes = new ArrayList<>();
        for(String id : idsPropostas)
            hashcodes.add( id.hashCode() );
        return hashcodes;
    }

    public static Candidaturas parseCandidaturaCSV(String str) throws Exception {

        ArrayList<String> values = FileUtils.splitLineCSV( str );
        if( values.size() < 2)
            throw new Exception("Must have at least 2 values!");

        try {
            return new Candidaturas(
                    Long.parseLong(values.get(0)),
                    values.subList(1, values.size())
            );
        } catch (NumberFormatException e){
            throw new Exception("Error in values");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(getClass() != obj.getClass())
            return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Long.valueOf(idAluno).hashCode();
    }

    @Override
    public String toString() {
        return String.join("    ", String.valueOf(idAluno), idsPropostas.toString() );
    }

}
