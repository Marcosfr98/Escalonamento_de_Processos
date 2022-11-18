import java.util.Scanner;

public class EscalonamentoProcessos {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o número de processos: ");
        Integer numero_processos = entrada.nextInt();
        entrada.nextLine();

        Integer[] tempo_execucao = new Integer[numero_processos];
        Integer[] tempo_chegada = new Integer[numero_processos];
        Integer[] tempo_espera = new Integer[numero_processos];
        Integer[] tempo_restante = new Integer[numero_processos];

        Integer processo_em_execucao = 0;
        Integer processos_terminados = 0;

        System.out.println("---------- Opções ----------");
        System.out.println("1) Atribuição automática");
        System.out.println("2) Atribuição manual");

        System.out.print("OPÇÃO: ");
        Integer atribuicao = entrada.nextInt();
        entrada.nextLine();

        if (atribuicao.equals(1)) {
            //Atribuição automática
        } else if (atribuicao.equals(2)) {
            for (Integer i = 0; i < numero_processos; i++) {
                System.out.print("Digite o tempo de execução do processo (p" + (i + 1) + "): ");
                tempo_execucao[i] = entrada.nextInt();
                tempo_restante[i] = tempo_execucao[i];

                System.out.print("Digite o tempo de chegada do processo (p" + (i + 1) + "): ");
                tempo_chegada[i] = entrada.nextInt();
            }

        }
        System.out.println("---------- ESCOLHA O TIPO DE ALGORITMO ----------");
        System.out.println("1) FCFS");
        System.out.println("2) SJF Não-Premptivo");
        System.out.println("3) SJF Premptivo");
        System.out.print("OPÇÃO: ");
        Integer tipo_algoritmo = entrada.nextInt();
        if (tipo_algoritmo.equals(1)) {
            //FCFS

            for (Integer i = 0; i < 1000; i++) {
                if (tempo_restante[processo_em_execucao] != 0) {
                    System.out.println(i + "-" + (processo_em_execucao + 1));
                    tempo_restante[processo_em_execucao]--;
                    if (tempo_restante[processo_em_execucao] == 0) {
                        processos_terminados++;

                        if (processos_terminados == numero_processos)
                            break;
                        else
                            processo_em_execucao++;
                    }
                }


            }

        } else if (tipo_algoritmo.equals(2)) {


        } else if (tipo_algoritmo.equals(3)) {
            //SJF Premptivo
            Integer indice_processo_menor_tempo_execucao = 0, menor_tempo_restante;
            processos_terminados = 0;

            for(int instante_tempo = 0; instante_tempo < 1000; instante_tempo++){
                menor_tempo_restante=1000;
                for(int processo = 0; processo < numero_processos; processo++){
                    if(tempo_restante[processo] != 0) {
                        if (tempo_chegada[processo] <= instante_tempo) {
                            if (tempo_restante[processo] < menor_tempo_restante) {
                                indice_processo_menor_tempo_execucao = processo;
                                menor_tempo_restante = tempo_restante[processo];
                            }
                        }
                    }
                }
                System.out.println(instante_tempo + "-" + (indice_processo_menor_tempo_execucao + 1));
                if(tempo_restante[indice_processo_menor_tempo_execucao] > 1) {
                    tempo_restante[indice_processo_menor_tempo_execucao]--;
                    //System.out.println("tempo_restante ["+indice_processo_menor_tempo_execucao+"] = "+tempo_restante[indice_processo_menor_tempo_execucao]);
                }
                else{
                    tempo_restante[indice_processo_menor_tempo_execucao]--;
                    if(numero_processos == (processos_terminados+1)) {
                        break;
                    }
                    else{
                        processos_terminados++;
                    }
                    System.out.println("tempo_restante ["+indice_processo_menor_tempo_execucao+"] = "+tempo_restante[indice_processo_menor_tempo_execucao]+" processos_terminados="+processos_terminados);

                }
            }
        }
    }
}
