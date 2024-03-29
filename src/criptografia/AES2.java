package criptografia;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class AES2 {

	private static String chaveSimetrica;
	private static String mensagem;
	private static SecretKey key;
	private static byte[] mensagemEncriptada;
	private static byte[] mensagemDescriptada;

	public static void main(String args[]) {
		Object[] opcoes = { "criptografar", "descriptograr" };
		Object resposta;
		/**
		 * Solicita ao usuário que informe uma chave com caracteres: (256 / 8 =
		 * 32) 32 caracteres = 256 bits (192 / 8 = 192) 24 caracteres = 192 bits
		 * (128 / 8 = 128) 16 caracteres = 128 bits
		 */

		/*
		 * System.out.println("32 caracteres = chave com 256 bits" +
		 * "\n24 caracteres = chave com 192 bits" +
		 * "16 caracteres = chave com 128 bits" + "\n Infomre uma Chave: ");
		 */
		chaveSimetrica = JOptionPane.showInputDialog(null,
				"32 caracteres = chave com 256 bits"
						+ "\n24 caracteres = chave com 192 bits"
						+ "\n16 caracteres = chave com 128 bits"
						+ "\n Infomre uma Chave:", "Insira a chave",
				JOptionPane.PLAIN_MESSAGE);

		key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");

		resposta = JOptionPane.showInputDialog(null,
				"Escolha a operação que deseja", "Encriptagem simétrica",
				JOptionPane.PLAIN_MESSAGE, null, opcoes, "criptografar");

		try {
			if (resposta.equals("criptografar")) {

				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, key);
				/* Solicita ao usuário que informe sua mensagem a ser encriptada */
				mensagem = JOptionPane.showInputDialog(null,
						"Informe sua mensagem a ser encriptada: ",
						"Insira a Mensagem", JOptionPane.PLAIN_MESSAGE);
				/* Encripta a Mensagem */
				mensagemEncriptada = cipher.doFinal(mensagem.getBytes());
				/* Exibe Mensagem Encriptada */
				JOptionPane.showMessageDialog(null, ("Mensagem Encriptada: "
						+ mensagemEncriptada));

			} else {

				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, key);
				/* Solicita ao usuŕio que informe sua mensagem encriptada */
				mensagem = JOptionPane.showInputDialog(null,
						"Informe sua mensagem a ser encriptada: ",
						"Insira a Mensagem", JOptionPane.PLAIN_MESSAGE);
				/* Encripta a Mensagem */
				mensagemEncriptada = cipher.doFinal(mensagem.getBytes());
				/* Informa ao objeto a ação de desencriptar */
				cipher.init(Cipher.DECRYPT_MODE, key);
				/* Recebe a mensagem encriptada e descripta */
				mensagemDescriptada = cipher.doFinal(mensagemEncriptada);
				/**
				 * Converte para a base 64 e amazena a mensagem em uma variavel
				 * auxiliar
				 */
				String mensagemOriginal = new String(mensagemDescriptada);

				/* Exibe Mensagem Descriptada */
				JOptionPane.showMessageDialog(null, "Mensagem Descriptada: " + mensagemOriginal);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}