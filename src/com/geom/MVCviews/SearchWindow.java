package com.geom.MVCviews;


import javax.swing.JInternalFrame;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerListModel;

import com.geom.MVCModel.Model;
import com.geom.database.DAOFactory;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class SearchWindow extends JInternalFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JButton btnSearch;
	private ArrayList<Integer>keys;
	private JSpinner comboBox;

	public SearchWindow() {
		initComponent();

	}
	public JSpinner getcomboBox(){
		return this.comboBox;
	}
	public ArrayList<Integer>getKeys(){
		return this.keys;
	}
	public void setKeys(ArrayList<Integer>keys){
		this.keys=keys;
	}
	public void createEvents(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
	public JButton getSearchBtn(){
		return this.btnSearch;
	}
	public JTextField getTextField(){
		return this.textField;
	}
	public void initComponent(){
		setClosable(true);
		setTitle("Form");
		setBounds(0, 0, 200, 132);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		textField = new JTextField();
		textField.setToolTipText("Tap IDFigure here");
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		if(keys ==null)
			keys = DAOFactory.getKeys("Figure");
		SpinnerListModel listKeys = new SpinnerListModel(keys);
		comboBox = new JSpinner(listKeys);
		comboBox.setToolTipText("List of  recent keys in DB");
		//mise en forme
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(btnSearch))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	public void update(Observable o, Object arg) {
		this.comboBox.setModel(new SpinnerListModel(((Model)o).getListKeys()));
		textField.setText(((Model)o).getiDFigure());
		this.repaint();
	}
}
