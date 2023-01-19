import java.util.Random;
import java.util.Scanner;

public class EscalonamentoDeProcessos {
	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.print("Digite o número de processos: ");
		Integer numero_processos = entrada.nextInt();
		entrada.nextLine();

		Integer[] tempo_execucao = new Integer[numero_processos];
		Integer[] tempo_chegada = new Integer[numero_processos];
		Integer[] tempo_espera = new Integer[numero_processos];
		Integer[] tempo_restante = new Integer[numero_processos];
		Integer tempo_volta[] = new Integer[numero_processos];
		Integer tempo_sistema = 0;
		Integer total_processos = 0;
		Integer sinalizacao[] = new Integer[numero_processos];

		Integer processo_em_execucao = 0;
		Integer processos_terminados = 0;

		System.out.println("---------- Opções ----------");
		System.out.println("1) Atribuição automática");
		System.out.println("2) Atribuição manual");

		System.out.print("OPÇÃO: ");
		Integer atribuicao = entrada.nextInt();
		entrada.nextLine();

		if (atribuicao.equals(1)) {
			Random gerador = new Random();

			for (Integer i = 0; i < numero_processos; i++) {
				System.out.print("Digite o tempo de execução do processo (p" + (i + 1) + "): ");
				tempo_execucao[i] = gerador.nextInt(10);
				tempo_restante[i] = tempo_execucao[i];

				System.out.print("Digite o tempo de chegada do processo (p" + (i + 1) + "): ");
				tempo_chegada[i] = entrada.nextInt();
			}
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
			// FCFS

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
			// SJF Não-Premptivo
			boolean a = true;
			while (true) {
				int c = numero_processos, min = 999;
				if (total_processos == numero_processos)
					break;
				for (int i = 0; i < numero_processos; i++) {
					if ((tempo_chegada[i] <= tempo_sistema) && (sinalizacao[i] == 0) && (tempo_execucao[i] < min)) {
						min = tempo_execucao[i];
						c = i;
					}
				}
				if (c == numero_processos)
					tempo_sistema++;
				else {
					tempo_restante[c] = tempo_sistema + tempo_execucao[c];
					tempo_sistema += tempo_execucao[c];
					tempo_volta[c] = tempo_restante[c] - tempo_chegada[c];
					tempo_espera[c] = tempo_volta[c] - tempo_execucao[c];
					processos_terminados = 1;
					total_processos++;
				}
			}
			for (int i = 0; i < numero_processos; i++) {
				System.out.println(tempo_chegada[i] + "\t" + tempo_execucao[i] + "\t" + tempo_restante[i] + "\t"
						+ tempo_volta[i] + "\t" + tempo_espera[i]);
			}
			entrada.close();

		} else if (tipo_algoritmo.equals(3)) {
			// SJF Premptivo
			Integer indice_processo_menor_tempo_execucao = 0, menor_tempo_restante;
			processos_terminados = 0;

			for (int instante_tempo = 0; instante_tempo < 1000; instante_tempo++) {
				menor_tempo_restante = 1000;
				for (int processo = 0; processo < numero_processos; processo++) {
					if (tempo_restante[processo] != 0) {
						if (tempo_chegada[processo] <= instante_tempo) {
							if (tempo_restante[processo] < menor_tempo_restante) {
								indice_processo_menor_tempo_execucao = processo;
								menor_tempo_restante = tempo_restante[processo];
							}
						}
					}
				}
				System.out.println(instante_tempo + "-" + (indice_processo_menor_tempo_execucao + 1));
				if (tempo_restante[indice_processo_menor_tempo_execucao] > 1) {
					tempo_restante[indice_processo_menor_tempo_execucao]--;
					// System.out.println("tempo_restante ["+indice_processo_menor_tempo_execucao+"]
					// = "+tempo_restante[indice_processo_menor_tempo_execucao]);
				} else {
					tempo_restante[indice_processo_menor_tempo_execucao]--;
					if (numero_processos == (processos_terminados + 1)) {
						break;
					} else {
						processos_terminados++;
					}
					System.out.println("tempo_restante [" + indice_processo_menor_tempo_execucao + "] = "
							+ tempo_restante[indice_processo_menor_tempo_execucao] + " processos_terminados="
							+ processos_terminados);

				}
			}
		} else if (tipo_algoritmo.equals(6)) {
			// Round-Robin
			int n, i, qt, count = 0, temp, sq = 0, bt[], wt[], tat[], rem_bt[];
			float awt = 0, atat = 0;
			bt = new int[10];
			wt = new int[10];
			tat = new int[10];
			rem_bt = new int[10];
			Scanner s = new Scanner(System.in);
			System.out.print("Digite o número máximo de processos (máximo 10) = ");
			n = s.nextInt();
			System.out.print("Digite o tempo de execução do processo = \n");
			for (i = 0; i < n; i++) {
				System.out.print("P" + i + " = ");
				bt[i] = s.nextInt();
				rem_bt[i] = bt[i];
			}
			System.out.print("Digite a quantidade de tempo: ");
			qt = s.nextInt();
			while (true) {
				for (i = 0, count = 0; i < n; i++) {
					temp = qt;
					if (rem_bt[i] == 0) {
						count++;
						continue;
					}
					if (rem_bt[i] > qt)
						rem_bt[i] = rem_bt[i] - qt;
					else if (rem_bt[i] >= 0) {
						temp = rem_bt[i];
						rem_bt[i] = 0;
					}
					sq = sq + temp;
					tat[i] = sq;
				}
				if (n == count)
					break;
			}
			System.out.print("--------------------------------------------------------------------------------");
			System.out.print("\n Processos \t     Tempo de execução \t       Tempo da Virada \t          Tempo de espera \n");
			System.out.print("--------------------------------------------------------------------------------");
			for (i = 0; i < n; i++) {
				wt[i] = tat[i] - bt[i];
				awt = awt + wt[i];
				atat = atat + tat[i];
				System.out.print("\n " + (i + 1) + "\t " + bt[i] + "\t\t " + tat[i] + "\t\t " + wt[i] + "\n");
			}
			awt = awt / n;
			atat = atat / n;
			System.out.println("\n Média de tempo de espera = " + awt + "\n");
			System.out.println(" Média de tempo de virada = " + atat);
		}
	}
}
