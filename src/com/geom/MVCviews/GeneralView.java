package com.geom.MVCviews;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JDesktopPane;
import javax.swing.border.EtchedBorder;

import com.geom.MVCModel.Model;
import com.geom.model.Figure;
/**
 * Implements the Interface containing board, buttons and forms where the user can input his settings
 * Built with WindowBuilder
 * @author Dasha
 *
 */
public class GeneralView extends JFrame implements Observer{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textX,textY,textType,textPerimeter,textArea;
	private JList<Double> table;
	private JButton buttonPrev,buttonNext, btnV,btnX;
	private JMenuItem mntmSave,mntmLoad;
	private JDesktopPane desktopPane;
	private SearchWindow form;
	private Board board;

	public JList<Double> getListl(){
		return this.table;
		
	}
	public JButton getBtnX(){
		return this.btnX;
	}
	public JButton getBtnV(){
		return this.btnV;
	}
	public JDesktopPane getDesktopPane(){
		return this.desktopPane;
	}
	public void setListL(Double[] ds){
		this.table.setListData(ds);
	}
	public Board getBoard(){
		return this.board;
	}
	public void setTypeText(String t){
		this.textType.setText(t);
	}
	public void setBoard(Board board){
		this.board=board;
	}
	public SearchWindow getForm(){
		return this.form;
	}
	public void showForm(){
		form.show();
	}
	public void setForm(SearchWindow form){
		this.form=form;
	}
	public void addformtodesktoppane(SearchWindow window){
		this.desktopPane.add(window);
		
	}
	public String getTextXString(){
		return this.textX.getText();
	}
	public String getTextYString(){
		return this.textY.getText();
	}
	
	public void setTextX(String s){
		this.textX.setText(s);
	}
	public void setTextY(String t){
		this.textY.setText(t);
	}
	public void setTextPerimeter(String t){
		this.textPerimeter.setText(t);
	}
	public void setTextArea(String t){
		this.textArea.setText(t);
	}
	public JMenuItem getmntmSave(){
		return this.mntmSave;
	}
	public void setmntmSave(JMenuItem se){
		this.mntmSave=se;
	}
	public JMenuItem getmntmLoad(){
		return this.mntmLoad;
	}
	public void setmntmLoad(JMenuItem se){
		this.mntmLoad=se;
	}
	public JButton getbuttonPrev(){
		return this.buttonPrev;
	}
	public void setbuttonPrev(JButton set){
		this.buttonPrev= set;
	}
	public JButton getbuttonNext(){
		return this.buttonNext;
	}
	public void setbuttonNext(JButton set){
		this.buttonNext=set;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralView frame = new GeneralView();
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
	public GeneralView() {
		setResizable(false);
		initComponent();
	}
	
	public void initComponent(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 455);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		setJMenuBar(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		menuBar.add(mnOptions);
		
		mntmSave = new JMenuItem("Save");
		
		mnOptions.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		
		mnOptions.add(mntmLoad);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		board = new Board();
		board.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		board.setBackground(Color.WHITE);
		
		buttonPrev = new JButton("<");
		
		
		textX = new JTextField();
		textX.setToolTipText("Type the X coordinate");
		textX.setColumns(10);
		
		textY = new JTextField();
		textY.setToolTipText("Type the Y coordinate");
		textY.setColumns(10);
		
		buttonNext = new JButton(">");
		
		
		JLabel lblType = new JLabel("Type");
		
		textType = new JTextField();
		textType.setToolTipText("Type of figure");
		textType.setEditable(false);
		textType.setColumns(10);
		
		JLabel lblPerimeter = new JLabel("Perimeter");
		
		textPerimeter = new JTextField();
		textPerimeter.setToolTipText("Perimeter of Figure");
		textPerimeter.setEditable(false);
		textPerimeter.setColumns(10);
		
		JLabel lblArea = new JLabel("Area");
		
		textArea = new JTextField();
		textArea.setEditable(false);
		textArea.setColumns(10);
		
		
		JLabel lblLengths = new JLabel("Lengths");
		
		
		table = new JList<Double>();
		table.setToolTipText("Scroll to see all");
		table.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(table);
		
		desktopPane = new JDesktopPane();
		form = new SearchWindow();
		form.setBackground(new Color(248, 248, 255));
		this.addformtodesktoppane(this.getForm());
		form.show();
		desktopPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		desktopPane.setBackground(new Color(240, 248, 255));
		
		btnV = new JButton("V");
		btnV.setToolTipText("Click after selecting a point, and specifying coordinate to change its location");
		btnV.setForeground(new Color(124, 252, 0));
		
		btnX = new JButton("X");
		btnX.setToolTipText("Click after selecting a point to delete it");
		btnX.setForeground(UIManager.getColor("Button.select"));
		btnX.setBackground(UIManager.getColor("Button.select"));
		
		//Mise en forme
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		//GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(board, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonPrev)
								.addComponent(btnX, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textY)
								.addComponent(textX, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnV, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonNext))))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblLengths)
						.addComponent(lblArea)
						.addComponent(lblPerimeter)
						.addComponent(lblType)
						.addComponent(textPerimeter)
						.addComponent(textArea)
						.addComponent(textType, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(pane, 0, 0, Short.MAX_VALUE))
					.addGap(3))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(board, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(buttonPrev)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnX))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(textX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(buttonNext)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnV)))
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblType)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPerimeter)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textPerimeter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblArea)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLengths)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pane, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);
	}
	@Override
	public void update(Observable o, Object arg) {
		Figure figure = ((Model)o).getFigureDrew(this.board.getWidth()/2, this.board.getHeight()/2);
		board.setFigure(figure);	
		board.repaint();
		this.setTypeText(figure.getType());
		this.setListL(((Model)o).getLengths());
		this.setTextArea(((Model)o).giveTextArea());
		this.setTextPerimeter(((Model)o).giveTextP());
	}
}
