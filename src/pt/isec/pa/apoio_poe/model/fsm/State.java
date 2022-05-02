package pt.isec.pa.apoio_poe.model.fsm;

public enum State {

    PHASE_ONE,
    PHASE_TWO,
    PHASE_THREE,
    PHASE_FOUR,
    PHASE_FIVE,

    // Fase 1
    GESTAO_ALUNOS,
    GESTAO_DOCENTES,
    GESTAO_PROPOSTAS,

    // Fase 2
    GESTAO_CANDIDATURAS,

    // Fase 4
    GESTAO_ORIENTADORES,

}
