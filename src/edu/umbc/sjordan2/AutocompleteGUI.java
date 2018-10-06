package edu.umbc.sjordan2;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class AutocompleteGUI extends JFrame implements ActionListener, DocumentListener {
	
	JPanel mainPanel, trainPanel, titlePanel, testPanelMain, testPanelSub1, testPanelSub2;
	JButton trainButton, testButton1, testButton2, testButton3, titleButton;
	JTextField trainInput, testInput;
	Label title;
	Font f;
	
	private static final long serialVersionUID = -8789699248173050589L;
	
	// This constructor, when called with no parameters, calls an alternate constructor
	// with arguments for the title, width, and height of the created JFrame.
	public AutocompleteGUI() {
		this("Asymmetrik Programming Challenge - Sean Jordan", 800, 400);
	}
	
	// This constructor creates all of the main GUI components of the application.
	public AutocompleteGUI(String frameTitle, int width, int height) {
		
		// Sets the basic characteristics of the created JFrame.
		setTitle(frameTitle);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creates the panel that holds the "training" objects, such as the train input
		// text field and the "Train" button.
		trainPanel = new JPanel();
		trainPanel.setLayout(new GridLayout(1, 2));
		trainButton = new JButton("Train");
		trainButton.addActionListener(this);
		trainInput = new JTextField(10);
		trainPanel.setBorder(BorderFactory.createTitledBorder("TRAIN the autocomplete algorithm!"));
		trainPanel.add(trainInput);
		trainPanel.add(trainButton);
		
		// Creates the panel that holds the "title" objects, such as the title phrase and
		// the button that shows the list of candidates and their confidence levels.
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 2));
		titleButton = new JButton("Show Current Candidates");
		titleButton.addActionListener(this);
		title = new Label("Mobile Device Keyboard Application");
		titlePanel.add(title);
		titlePanel.add(titleButton);
		f = new Font("TimesRoman", Font.BOLD, 22);
		title.setFont(f);
		
		// Creates the main "Test" panel that holds both sub-panels of the "test" objects.
		testPanelMain = new JPanel();
		testPanelMain.setBorder(BorderFactory.createTitledBorder("TEST the autocomplete algorithm!"));
		testPanelMain.setLayout(new BoxLayout(testPanelMain, BoxLayout.PAGE_AXIS));
		
		// Creates a new JPanel that holds the text input field for the test portion of this
		// application. This also registers a new Document Listener for the text field, which
		// permits update events every time the text field is changed. 
		testPanelSub1 = new JPanel();
		testPanelSub1.setLayout(new GridLayout(1, 1));
		testInput = new JTextField(10);
		testInput.getDocument().addDocumentListener(this);
		testPanelSub1.add(testInput);
		
		// Creates a new JPanel that holds the three "Autocomplete" buttons that get updated
		// each time the "train" text field is changed. 
		testPanelSub2 = new JPanel();
		testPanelSub2.setLayout(new GridLayout(1, 3));
		testButton1 = new JButton("");
		testButton1.addActionListener(this);
		testButton2 = new JButton("");
		testButton2.addActionListener(this);
		testButton3 = new JButton("");
		testButton3.addActionListener(this);
		testPanelSub2.add(testButton1);
		testPanelSub2.add(testButton2);
		testPanelSub2.add(testButton3);
		
		// Adds both of the sub-panels of the test portion to the main JPanel.
		testPanelMain.add(testPanelSub1);
		testPanelMain.add(testPanelSub2);
		
		// Adds the title, train, and test panels to the main JPanel of the GUI.
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(titlePanel);
		mainPanel.add(trainPanel);
		mainPanel.add(testPanelMain);
		setContentPane(mainPanel);
		
		// Does some final tinkering to the JFrame by packing it and making it look pretty
		// as well as making it visible to the user.
		pack();
		setVisible(true);
	}
	
	// This is the implementation of the action listener of the application, which 
	// listens for button clicks only.
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// If the button that says "Train" is clicked, then the text currently in the train input
		// text field is passed to the train() function in the AutocompleteProvider class, which
		// registers those words as candidates. If there is no text in the text field when the
		// button is clicked, then the button shows an error message for a period of time.
		if(e.getSource().equals(trainButton)) {
			JButton clickedButton = (JButton) e.getSource();
			TimerTask trainButtonReset = new TimerTask() {
				@Override
				public void run() {
					clickedButton.setText("Train");
				}
			};
			Timer buttonTimer = new Timer();
			String passage = trainInput.getText();
			if(passage.equals("")) {
				clickedButton.setText("ERROR: No Text in Input!");
				buttonTimer.schedule(trainButtonReset, 3000);
			} else {
				AutocompleteProvider acp = new AutocompleteProvider();
				trainInput.setText("");
				acp.train(passage);
				updateButtons();
				clickedButton.setText("Algorithm Successfully Trained!");
				buttonTimer.schedule(trainButtonReset, 3000);
			}
			
			// If the user clicks the button entitled "Show Current Candidates", then a new 
			// dialog is created and showed to the user. This dialog contains a table that
			// lists each candidate currently in memory. This is shown in the format of a
			// n by 2 table, where n is the number of candidates currently in memory, and
			// 2 is the amount of columns (one for words, one for confidences). It serves 
			// as an easy way to see what the test code is pulling its data from.
		} else if(e.getSource().equals(titleButton)) {
			String[] header = {"Candidate", "Confidence"};
			
			String[][] candidates = new String[CandidateUtils.candidatesList.size()][2];
			for(int i = 0; i < CandidateUtils.candidatesList.size(); i++) {
				candidates[i][0] = CandidateUtils.candidateWords.get(i).toLowerCase();
				candidates[i][1] = CandidateUtils.candidateConfidences.get(i).toString();
			}
			DefaultTableModel candidateModel = new DefaultTableModel(candidates, header);
			JTable candidateModelTable = new JTable(candidateModel);
			JScrollPane jsp = new JScrollPane(candidateModelTable);
			JOptionPane.showMessageDialog(mainPanel, jsp, "Current Candidate List", JOptionPane.PLAIN_MESSAGE);
			
			// If any of the three buttons are clicked, then the word that button displays
			// is sent to the train() algorithm in the AutocompleteProvider. The text input
			// field for testing is also cleared, and the buttons are updated to reflect the
			// updated candidate list. This provides "online" training, where the user can
			// submit additional words for training at the same time as they are testing.
		} else if(e.getSource().equals(testButton1) || e.getSource().equals(testButton2) || e.getSource().equals(testButton3)) {
			if(testInput.getText() != "") {
				AutocompleteProvider acp = new AutocompleteProvider();
				acp.train(((JButton) e.getSource()).getText());
				testInput.setText("");
				updateButtons();
			}
		}
	}
	
	// These three functions provide implementation for the Document Listener, which provides the
	// ability to trigger an event whenever a text field is edited. Much like current mobile
	// devices, where each key pressed brings up new autocomplete candidates, this Document
	// Listener allows each new character typed in the test input field to update the three
	// buttons, which is the list of most prominent autocomplete candidates.
	@Override
	public void insertUpdate(DocumentEvent e) {
		updateButtons();
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		updateButtons();	
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		updateButtons();
	}
	
	// This function updates the three buttons in the test panel to use the latest list of
	// autocomplete candidates in memory. Each new train/test input changes the list of
	// viable candidates for the user (and their confidences), and the buttons must be
	// reflective of this changed structure. If there is a case where there is not 
	// enough autocomplete candidates to fill the three buttons, the current input
	// is used as text for one of the buttons, and the rest are left blank. This is meant
	// to ease additions to the training list, and also mimics how mobile devices work.
	public void updateButtons() {
		String currentTestInput = testInput.getText();
		AutocompleteProvider acp = new AutocompleteProvider();
		List<Candidate> updatedCandidateList = acp.getWords(currentTestInput);
		if(updatedCandidateList.size() > 0) {
			testButton1.setText(updatedCandidateList.get(0).getWord());
			if(updatedCandidateList.size() > 1) {
				testButton2.setText(updatedCandidateList.get(1).getWord());
				if(updatedCandidateList.size() > 2) {
					testButton3.setText(updatedCandidateList.get(2).getWord());
				} else {
					testButton3.setText(currentTestInput);
				}
			} else {
				testButton2.setText(currentTestInput);
				testButton3.setText("");
			}
		} else {
			testButton1.setText(currentTestInput);
			testButton2.setText("");
			testButton3.setText("");
		}
	}

}
