package pt.isec.pa.apoio_poe.utils;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.Docente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static ArrayList<Proposta> readPropostasFromCSV(String filename) throws Exception {

        StringBuilder errors = new StringBuilder();
        ArrayList<Proposta> propostas = new ArrayList<>();

        ArrayList<String> lines = readFileLines(filename);
        int lineNum = 1;

        for (String line : lines){
            try{
                ArrayList<String> values = FileUtils.splitLineCSV( line );
                Proposta tmp;
                if( values.get(0).equalsIgnoreCase("T1") )
                    tmp = Estagio.parseEstagioCSV( values );
                else if( values.get(0).equalsIgnoreCase("T2") )
                    tmp = Projeto.parseProjetoCSV( values );
                else if( values.get(0).equalsIgnoreCase("T3") )
                    tmp = AutoProposto.parseProjetoCSV( values );
                else
                    throw new Exception("Unknown type. Must be T1, T2 or T3");

                propostas.add( tmp );

            } catch (Exception e){
                errors.append("\n").append(String.format("[%d] - %s - %s", lineNum, line, e.getMessage()));
            }
        }
        if( ! errors.isEmpty() )
            throw new Exception("Found errors in the following lines:" + errors);

        return propostas;
    }

    public static ArrayList<Aluno> readAlunosFromCSV(String filename) throws Exception {

        StringBuilder errors = new StringBuilder();
        ArrayList<Aluno> alunos = new ArrayList<>();

        ArrayList<String> lines = readFileLines(filename);
        int lineNum = 1;

        for (String line : lines){
            try{
                alunos.add( Aluno.parseAlunoCSV(line) );
            } catch (Exception e){
                errors.append("\n").append(String.format("[%d] - %s - %s", lineNum, line, e.getMessage()));
            }
        }
        if( ! errors.isEmpty() )
            throw new Exception("Found errors in the following lines:" + errors);

        return alunos;
    }

    public static ArrayList<Docente> readDocentesFromCSV(String filename) throws Exception {

        StringBuilder errors = new StringBuilder();
        ArrayList<Docente> docentes = new ArrayList<>();

        ArrayList<String> lines = readFileLines(filename);
        int lineNum = 1;

        for (String line : lines){
            try{
                docentes.add( Docente.parseDocenteCSV(line) );
            } catch (Exception e){
                errors.append("\n").append(String.format("[%d] - %s - %s", lineNum, line, e.getMessage()));
            }
        }
        if( ! errors.isEmpty() )
            throw new Exception("Found errors in the following lines:" + errors);

        return docentes;
    }

    public static ArrayList<Candidaturas> readCandidaturasFromCSV(String filename) throws Exception {

        StringBuilder errors = new StringBuilder();
        ArrayList<Candidaturas> candidaturas = new ArrayList<>();

        ArrayList<String> lines = readFileLines(filename);
        int lineNum = 1;

        for (String line : lines){
            try{
                candidaturas.add( Candidaturas.parseCandidaturaCSV(line) );
            } catch (Exception e){
                errors.append("\n").append(String.format("[%d] - %s - %s", lineNum, line, e.getMessage()));
            }
        }
        if( ! errors.isEmpty() )
            throw new Exception("Found errors in the following lines:" + errors);

        return candidaturas;
    }

    public static ArrayList<String> splitLineCSV(String str) {
        return new ArrayList<>( List.of( str.split(",") ) );
    }

//    public static ArrayList<ArrayList<String>> readCSV(String filename) throws Exception {
//
//        ArrayList<ArrayList<String>> content = new ArrayList<>();
//
//        ArrayList<String> lines = readFileLines( filename );
//
//        StringBuilder errors = new StringBuilder();
//        int lineNum = 1;
//
//        for(String line : lines){
//            ArrayList<String> lineContent = splitLineCSV(line);
//            if( lineContent.size() <= 1 )
//                errors.append("\n").append( String.format("[%d] - %s - Not enough parameters", lineNum, line) );
//            else
//                content.add( lineContent );
//            lineNum++;
//        }
//        if( ! errors.isEmpty() )
//            throw new Exception("Found errors in the following lines:" + errors);
//
//        return content;
//    }

    private static ArrayList<String> readFileLines(String filename) throws Exception {

        ArrayList<String> lines = new ArrayList<>();

        try ( BufferedReader in = openFile(filename) ) {
            String line;
            while ((line = in.readLine()) != null)
                lines.add(line);
        } catch (FileNotFoundException e) {
            throw new Exception("File '" + filename + "' was not found.");
        }
        return lines;
    }

    private static BufferedReader openFile(String filename) throws FileNotFoundException {
        return new BufferedReader( new FileReader( filename ) );
    }
}
