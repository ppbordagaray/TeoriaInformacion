package teoInfo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import teoInfo.model.huffman.CodigoHuffman;
import teoInfo.model.huffman.Simbolo;
import teoInfo.model.metricas.Media;
import teoInfo.util.Util;

public class Vista {

	public static void main(String[] args) {
		
		final JTextArea trabajo = new JTextArea("");
		trabajo.setEditable(false);
		final List<Simbolo> fs=new ArrayList();
		
		JButton botonPrueba = new JButton( "Generar codigos" );
		botonPrueba.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				
				String data = new String("Codigo Huffman:\n");
		  		String fuente = new String("Fuente:\n");
		  		if (Util.fValida(fs)){
			  		CodigoHuffman codHuf=new CodigoHuffman();
			  		HashMap<Simbolo,String> codificacion=new HashMap(codHuf.generarCodigoHuffman(fs));
			  		for(int i=0; i<fs.size(); i++){
			  			fuente = fuente + fs.get(i).toString()+"\n";
			  			data = data +"codigos de "+fs.get(i).getSimb()+": "+codificacion.get(fs.get(i)) + " de longitud: "+ ((String)codificacion.get(fs.get(i))).length()+"\n";
			  		}
			  		data =fuente+"\n"+data +"\nLongitud Media: "+ Media.calcular(fs, codificacion);
			  	} else {
			  		data = "Fuente Invalida";
			  	}
		  		trabajo.setText(data);
		  	}
		} );
		//Panel de Fuente
		SpringLayout layout = new SpringLayout();
		JPanel simPanel = new JPanel(layout);
		//Adjust constraints for the label so it's at (5,5).
		JLabel lSimb = new JLabel("Simbolo: ");
        final JTextField tFSimb = new JTextField("Ingrese un caracter",5);
        tFSimb.selectAll();
		layout.putConstraint(SpringLayout.WEST, lSimb,5,SpringLayout.WEST,simPanel);
        layout.putConstraint(SpringLayout.NORTH, lSimb,5,SpringLayout.NORTH, simPanel);
        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, tFSimb,5,SpringLayout.EAST, lSimb);
        layout.putConstraint(SpringLayout.NORTH, tFSimb,5,SpringLayout.NORTH, simPanel);
        //Adjust constraints for the content pane: Its right
        //edge should be 5 pixels beyond the text field's right
        //edge, and its bottom edge should be 5 pixels beyond
        //the bottom edge of the tallest component (which we'll
        //assume is textField).
        layout.putConstraint(SpringLayout.EAST, simPanel,5,SpringLayout.EAST, tFSimb);
        layout.putConstraint(SpringLayout.SOUTH, simPanel,5,SpringLayout.SOUTH, tFSimb);
        simPanel.add(lSimb);
        simPanel.add(tFSimb);
        //Probabilidad
        SpringLayout layoutProb = new SpringLayout();
		JPanel probPanel = new JPanel(layoutProb);
		//Adjust constraints for the label so it's at (5,5).
		JLabel lProb = new JLabel("Probabilidad: ");
        final JTextField tFProb = new JTextField("0.0000",5);
        tFProb.selectAll();
		layoutProb.putConstraint(SpringLayout.WEST, lProb,5,SpringLayout.WEST,probPanel);
        layoutProb.putConstraint(SpringLayout.NORTH, lProb,5,SpringLayout.NORTH, probPanel);
        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layoutProb.putConstraint(SpringLayout.WEST, tFProb,5,SpringLayout.EAST, lProb);
        layoutProb.putConstraint(SpringLayout.NORTH, tFProb,5,SpringLayout.NORTH, probPanel);
        //Adjust constraints for the content pane: Its right
        //edge should be 5 pixels beyond the text field's right
        //edge, and its bottom edge should be 5 pixels beyond
        //the bottom edge of the tallest component (which we'll
        //assume is textField).
        layoutProb.putConstraint(SpringLayout.EAST, probPanel,5,SpringLayout.EAST, tFProb);
        layoutProb.putConstraint(SpringLayout.SOUTH, probPanel,5,SpringLayout.SOUTH, tFProb);
        probPanel.add(lProb);
        probPanel.add(tFProb);
        
        JPanel simbolo = new JPanel(new FlowLayout());
        JButton bAddSimbolo = new JButton( "Agregar Simbolo" );
        bAddSimbolo.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				String  simbolo = tFSimb.getText();
				Double prob = new Double(tFProb.getText());
				Simbolo x=new Simbolo(simbolo.toCharArray()[0],prob.doubleValue());	  		
		  		fs.add(x);
		  		tFSimb.setText("Ingrese un caracter");
		  		tFSimb.selectAll();
		  		tFProb.setText("0.0000");
		  		tFProb.selectAll();
		  		
		  		String fuente = new String();
				for(int i=0; i<fs.size(); i++){
		  			fuente = fuente + fs.get(i).toString()+"\n";
		  		}
				trabajo.setText(fuente);
		  	}
		} );
        simbolo.add(simPanel);
        simbolo.add(probPanel);
        simbolo.add(bAddSimbolo);
        //*****Validar fuente*****************
        JButton validarFuente = new JButton( "Validar Fuente" );
        validarFuente.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				String fuente = new String();
				for(int i=0; i<fs.size(); i++){
		  			fuente = fuente + fs.get(i).toString()+"\n";
		  		}
				if (Util.fValida(fs)){
					fuente = fuente + "Fuente valida\n";
				} else {
					fuente = fuente + "Fuente Invalida\n";
				}
				trabajo.setText(fuente);
		  	}
		} );
        JButton limpiarFuente = new JButton( "Limpiar Fuente" );
        limpiarFuente.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				fs.clear();
				trabajo.setText("Se limpio la fuente");
		  	}
		} );
      //Panel de Fuente
      		//SpringLayout layoutBotonera = new SpringLayout();
      		JPanel botoneraPanel = new JPanel(new FlowLayout());
      		botoneraPanel.add(limpiarFuente);	
              botoneraPanel.add(validarFuente);
              botoneraPanel.add(botonPrueba);
        //************************************
		JFrame frame = new JFrame("Teoria de la Informacion");
		frame.setSize( 500,500 );   
		frame. setLayout( new BorderLayout() );
		frame.getContentPane().add( simbolo,BorderLayout.NORTH );
		frame.getContentPane().add( new JScrollPane( trabajo ),BorderLayout.CENTER );
		frame.getContentPane().add( botoneraPanel,BorderLayout.SOUTH );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
