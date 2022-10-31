package views;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TelaSplashCondominio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3356025952135839862L;
	private JPanel contentPane;
	private JProgressBar jProgressBarTelaSplash;
	private JLabel jLabelMostraProgresso;
	private Image img_logo = new ImageIcon(TelaSplashCondominio.class.getResource("/imagens/Logo-CondominioV3.png")).getImage()
			.getScaledInstance(400, 117, Image.SCALE_SMOOTH);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSplashCondominio frame = new TelaSplashCondominio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaSplashCondominio() {
		initComponents();	
		new Thread() {
			

			public void run() {
				for(int i = 0; i<101;i++) {
					try {
						sleep(60);
						jProgressBarTelaSplash.setValue(i);
						
						//40%
						if(jProgressBarTelaSplash.getValue() <= 40) {
							jLabelMostraProgresso.setText("Carregando banco de dados");
							
						//70%
						}else if(jProgressBarTelaSplash.getValue() <= 70) {
							jLabelMostraProgresso.setText("Carregando banco de dados");
							
						//100%
						}else if(jProgressBarTelaSplash.getValue() == 100) {
							jLabelMostraProgresso.setText("Conectando com o sistema");
							TelaPrincipalCondominio tela = new TelaPrincipalCondominio();
							tela.setVisible(true);
							TelaSplashCondominio.this.dispose();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Logger.getLogger(TelaSplashCondominio.class.getName()).log(Level.SEVERE,null,e);
					}
				}
			}
		}.start();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1084, 661);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(img_logo));
		lblNewLabel.setBounds(377, 187, 326, 264);
		panel.add(lblNewLabel);
		
		jProgressBarTelaSplash = new JProgressBar();
		jProgressBarTelaSplash.setBounds(377, 462, 326, 30);
		panel.add(jProgressBarTelaSplash);
		
		jLabelMostraProgresso = new JLabel("");
		jLabelMostraProgresso.setFont(new Font("Dialog", Font.BOLD, 15));
		jLabelMostraProgresso.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelMostraProgresso.setBounds(377, 491, 326, 30);
		panel.add(jLabelMostraProgresso);
	}
}
