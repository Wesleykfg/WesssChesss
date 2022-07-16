package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		System.out.println("                                #      #                  #\r\n"
				+ "#     #                         #      #           ####   #\r\n"
				+ "#  #  #   ###   ###   ###   ###  #    #    ###    #      # ##   ###   ###   ###\r\n"
				+ "#  #  #  #  #  #     #     #     #        #      #       ## #  #  #  #     #\r\n"
				+ "# ## #  #  #   ##    ##    ##     #       ##     #       #  # #  #   ##    ##\r\n"
				+ "# ## #  ###     ##    ##    ##     #       ##    #      #  #  ###     ##    ##\r\n"
				+ "## ##   #        #     #     #     #        #    #      #  #  #        #     #\r\n"
				+ "#  #     ###  ###   ###   ###       #    ###      ####  #  #   ###  ###   ###\r\n"
				+ "                                    #\r\n"
				+ "");
		System.out.println("Olá, seja bem vindo ao Wesss's Chess"
				+ "\nEspero que se divirta muito!"
				+ "\nMe esforcei o maximo para entender esse código,"
				+ "\nmas ainda sou novato nisso ok *-*"
				+ "\nÉ importante ressaltar que esse código tem sua"
				+ "\norigem no perfil do incrivel programador: GuilhermeManzano no GitHub!"
				+ "\nEdições, traduções e adaptações foram"
				+ "\nfeitas por mim Wesleykfg!"
				+ "\nConforme for me desenvolvendo, irei aperfeiçoar esse código!"
				+ "\nPorém no momento, estou muito feliz de conseguir entende-lo,"
				+ "\nreproduzi-lo e edita-lo da maneira que eu desejei!"
				+ "\ntenham um bom jogo!!!");
		System.out.println("***********************************************************************\n");
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Por gentileza, informe a peça que deseja mover: ");
				ChessPosition source = UI.readChessPosition(sc);
	
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Por gentileza, informe a casa alvo que deseja ir: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Digite a Peça para Promoção (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Atenção, valor incorreto! Digite a Peça para Promoção (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
				
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}
}
