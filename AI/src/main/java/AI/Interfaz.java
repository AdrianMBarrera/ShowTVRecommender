package AI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.omertron.tvrageapi.model.ShowInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Interfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DataModel model = new FileDataModel(new File("C:/Users/User/workspace/AI/src/main/java/AI/ShowTVList.csv"));
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setTitle("ShowRecApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 387);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(208, 89, 160, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JTextPane txtpn = new JTextPane();
		txtpn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpn.setEditable(false);
		txtpn.setBackground(Color.LIGHT_GRAY);
		txtpn.setText("ID de usuario:");
		txtpn.setBounds(61, 89, 137, 29);
		contentPane.add(txtpn);
		
		final JTextPane txtpnbienvenido = new JTextPane();
		txtpnbienvenido.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnbienvenido.setToolTipText("");
		txtpnbienvenido.setEditable(false);
		txtpnbienvenido.setBackground(Color.LIGHT_GRAY);
		txtpnbienvenido.setText("¡Bienvenido!");
		txtpnbienvenido.setBounds(36, 29, 168, 39);
		contentPane.add(txtpnbienvenido);
		
		final JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnNombre.setEditable(false);
		txtpnNombre.setBackground(Color.LIGHT_GRAY);
		txtpnNombre.setText("Nombre:");
		txtpnNombre.setBounds(61, 155, 102, 30);
		contentPane.add(txtpnNombre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 155, 160, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		ImageIcon Imagen = new ImageIcon("C:/Users/User/workspace/AI/img/television_icon.png");
		final JLabel Img = new JLabel(new ImageIcon("C:/Users/User/workspace/AI/img/television_icon.png"));
		Img.setBackground(Color.LIGHT_GRAY);
		Img.setVerticalAlignment(SwingConstants.TOP);
		Img.setLocation(405, 26);
		Img.setSize(200, 200);		
		contentPane.add(Img); 
		
		Icon icono = new ImageIcon(Imagen.getImage().getScaledInstance(Img.getWidth(), Img.getHeight(), Image.SCALE_DEFAULT));
		Img.setIcon(icono);
		
		final JButton btnRecomendar = new JButton("Recomendar!");
		btnRecomendar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		final JButton btnValorarSerie = new JButton("Valorar serie");
		btnValorarSerie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnRecomendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnbienvenido.setVisible(false);
				txtpn.setVisible(false);
				txtpnNombre.setVisible(false);
				textField.setVisible(false);
				textField_1.setVisible(false);
				btnRecomendar.setVisible(false);
				btnValorarSerie.setVisible(false);
				int id = Integer.parseInt(textField.getText());
				String userName = textField_1.getText();
				System.out.println(id);
				try {
					DataModel model = new FileDataModel(new File("C:/Users/User/workspace/AI/src/main/java/AI/ShowTVList.csv"));
					int tvShowID = ShowTVRecommender.recommenderFunction(model, id);
					ShowInfo show = ApiTVRage.getShowInfoInt(tvShowID);
					
					if (show.getShowName().equals("UNKNOWN")) {
						final JTextPane txtpnUnknown = new JTextPane();
						txtpnUnknown.setFont(new Font("Tahoma", Font.PLAIN, 16));
						txtpnUnknown.setEditable(false);
						txtpnUnknown.setBackground(Color.LIGHT_GRAY);
						txtpnUnknown.setText("Lo sentimos " + userName +", aún no podemos recomendarte nada...");
						txtpnUnknown.setBounds(70, 80, 220, 60);
						contentPane.add(txtpnUnknown);
						
						final JTextPane txtpnUnknown2 = new JTextPane();
						txtpnUnknown2.setFont(new Font("Tahoma", Font.PLAIN, 20));
						txtpnUnknown2.setEditable(false);
						txtpnUnknown2.setBackground(Color.LIGHT_GRAY);
						txtpnUnknown2.setText("¡Sigue votando!");
						txtpnUnknown2.setBounds(70, 175, 220, 60);
						contentPane.add(txtpnUnknown2);
						
						final JButton btnVolver = new JButton("Volver");
						btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
						btnVolver.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								txtpnUnknown.setVisible(false);
								txtpnUnknown2.setVisible(false);
								btnVolver.setVisible(false);
								txtpnbienvenido.setVisible(true);
								txtpn.setVisible(true);
								txtpnNombre.setVisible(true);
								textField.setVisible(true);
								textField_1.setVisible(true);
								btnRecomendar.setVisible(true);
								btnValorarSerie.setVisible(true);
							}
						});
						btnVolver.setBounds(108, 279, 150, 50);
						contentPane.add(btnVolver);
					}
					else {
						final JTextPane txtpnName = new JTextPane();
						txtpnName.setFont(new Font("Tahoma", Font.PLAIN, 16));
						txtpnName.setEditable(false);
						txtpnName.setBackground(Color.LIGHT_GRAY);
						
						txtpnName.setText(userName + ", te recomendamos: \n\n" +
						                  "      Nombre: " + show.getShowName() + 
						                  " (" + show.getStarted() + "-" + show.getEnded().substring(7, show.getEnded().length()) + ")\n" + 
						                  "      País: " + show.getCountry() + "\n" + 
						                  "      Temporadas: " + show.getTotalSeasons() + "\n" +
						                  "      Estado: " + show.getStatus());
						
						txtpnName.setBounds(50, 20, 300, 140);
						contentPane.add(txtpnName);
						
						String ImageShow = show.getImageLink();					
						URL url = new URL (ImageShow);
						Image imShow = ImageIO.read(url);
						ImageIcon imicon = new ImageIcon(imShow);
						
						final JLabel is = new JLabel(new ImageIcon(imShow));
						is.setBackground(Color.LIGHT_GRAY);
						is.setVerticalAlignment(SwingConstants.TOP);
						is.setLocation(100, 150);
						is.setSize(180, 180);		
						contentPane.add(is); 
						
						Icon icono2 = new ImageIcon(imicon.getImage().getScaledInstance(is.getWidth(), is.getHeight(), Image.SCALE_DEFAULT));
						is.setIcon(icono2);
						
						final JButton btnVolver = new JButton("Volver");
						btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
						btnVolver.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								txtpnName.setVisible(false);
								is.setVisible(false);
								btnVolver.setVisible(false);
								txtpnbienvenido.setVisible(true);
								txtpn.setVisible(true);
								txtpnNombre.setVisible(true);
								textField.setVisible(true);
								textField_1.setVisible(true);
								btnRecomendar.setVisible(true);
								btnValorarSerie.setVisible(true);
							}
						});
						btnVolver.setBounds(365, 279, 150, 50);
						contentPane.add(btnVolver);
					}
						
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRecomendar.setBounds(108, 279, 150, 50);
		contentPane.add(btnRecomendar);
		
		btnValorarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnbienvenido.setVisible(false);
				txtpn.setVisible(false);
				txtpnNombre.setVisible(false);
				textField.setVisible(false);
				textField_1.setVisible(false);
				btnRecomendar.setVisible(false);
				btnValorarSerie.setVisible(false);
				
				final int id = Integer.parseInt(textField.getText());
				
				final JTextPane txtpnName = new JTextPane();
				txtpnName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtpnName.setEditable(false);
				txtpnName.setBackground(Color.LIGHT_GRAY);
				txtpnName.setText("Serie:");
				txtpnName.setBounds(61, 89, 70, 29);
				contentPane.add(txtpnName);
				
				final JTextPane txtpnValue = new JTextPane();
				txtpnValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtpnValue.setEditable(false);
				txtpnValue.setBackground(Color.LIGHT_GRAY);
				txtpnValue.setText("Valor:");
				txtpnValue.setBounds(61, 155, 70, 30);
				contentPane.add(txtpnValue);
				
				final JTextField textField_name = new JTextField();
				textField_name.setBounds(150, 89, 160, 30);
				contentPane.add(textField_name);
				textField_name.setColumns(10);
				
				final JTextField textField_value = new JTextField();
				textField_value.setBounds(150, 155, 160, 30);
				contentPane.add(textField_value);
				textField_value.setColumns(10);
				
				final JButton btnVotar = new JButton("Votar");
				btnVotar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnVotar.setVisible(false);
						txtpnName.setVisible(false);
						txtpnValue.setVisible(false);
						textField_name.setVisible(false);
						textField_value.setVisible(false);
						
						String name = textField_name.getText();
						double value = Double.parseDouble(textField_value.getText());
						int idserie = ApiTVRage.searchShow(name);
						
						System.out.println("Usuario: "+id);
						System.out.println("Serie: "+name);
						System.out.println("IDSerie: "+idserie);
						System.out.println("Valor: "+value);
						
						if (idserie == 0) {
							final JTextPane txtpnUnknown = new JTextPane();
							txtpnUnknown.setFont(new Font("Tahoma", Font.PLAIN, 16));
							txtpnUnknown.setEditable(false);
							txtpnUnknown.setBackground(Color.LIGHT_GRAY);
							txtpnUnknown.setText("Ups! ... Parece que no hemos encontrado esta serie ...");
							txtpnUnknown.setBounds(70, 80, 220, 60);
							contentPane.add(txtpnUnknown);
							
							final JButton btnVolver = new JButton("Volver");
							btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
							btnVolver.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									txtpnUnknown.setVisible(false);
									btnVolver.setVisible(false);
									txtpnbienvenido.setVisible(true);
									txtpn.setVisible(true);
									txtpnNombre.setVisible(true);
									textField.setVisible(true);
									textField_1.setVisible(true);
									btnRecomendar.setVisible(true);
									btnValorarSerie.setVisible(true);
								}
							});
							btnVolver.setBounds(108, 279, 150, 50);
							contentPane.add(btnVolver);
						}
						else {
							try {
								FileWriter fichero = new FileWriter("C:/Users/User/workspace/AI/src/main/java/AI/ShowTVList.csv",true);
								PrintWriter pw = new PrintWriter(fichero);
								pw.print("\n" + id + "," + idserie + "," + value);
								fichero.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							txtpnbienvenido.setVisible(true);
							txtpn.setVisible(true);						
							txtpnNombre.setVisible(true);
							textField.setVisible(true);
							textField_1.setVisible(true);
							btnRecomendar.setVisible(true);
							btnValorarSerie.setVisible(true);	
						}
					}
				});
				btnVotar.setBounds(365, 279, 150, 50);;
				contentPane.add(btnVotar);
			}
		});
		btnValorarSerie.setBounds(365, 279, 150, 50);
		contentPane.add(btnValorarSerie);
	}
}
