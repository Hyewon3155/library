package Bookmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






public class test {

    private JFrame frame;
    private JTextField textField;
    private JTable table_1;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;


    private JTable table;
    private JTable table_2;
    private JTable table_3;
    private ArrayList<Book> bookList;
    private ArrayList<Friend> friendList;
    private ArrayList<Loan> loanList;
    private JPanel panel = new JPanel();
    private JPanel panel_1 = new JPanel();
    private JPanel panel_2 = new JPanel();
    private JPanel panel_3 = new JPanel();
    private JPanel panel_4 = new JPanel();
    private JPanel panel_5 = new JPanel();
    private JPanel panel_6 = new JPanel();
    private JPanel panel_7 = new JPanel();



    JComboBox<String> comboBox;
    JComboBox<String> comboBox_1;
    JComboBox<String> comboBox_2;
    JComboBox<String> comboBox_3;
    JComboBox<String> comboBox_4;

   

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test window = new test();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public test() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 719, 558);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setForeground(new Color(0, 0, 0));
        panel.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("혜원도서관");
        lblNewLabel.setFont(new Font("카페24 써라운드", Font.PLAIN, 20));
        lblNewLabel.setBounds(93, 41, 125, 43);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("도서 등록");
        lblNewLabel_1.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        lblNewLabel_1.setBounds(279, 52, 57, 21);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("도서 조회");
        lblNewLabel_1_1.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(356, 52, 57, 21);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("친구 등록");
        lblNewLabel_1_1_1.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        lblNewLabel_1_1_1.setBounds(427, 52, 57, 21);
        panel.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("대출 조회");
        lblNewLabel_1_1_2.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        lblNewLabel_1_1_2.setBounds(579, 52, 94, 21);
        panel.add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("친구 조회");
        lblNewLabel_1_1_1_1.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        lblNewLabel_1_1_1_1.setBounds(503, 52, 57, 21);
        panel.add(lblNewLabel_1_1_1_1);

        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(93, 94, 554, 427);
        panel.add(panel_1);
        panel_1.setLayout(null);

        textField = new JTextField();
        textField.setBounds(0, 8, 398, 21);
        panel_1.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("검색");
        btnNewButton.setBackground(new Color(245, 245, 245));
        btnNewButton.setFont(new Font("카페24 심플해", Font.BOLD, 17));
        btnNewButton.addActionListener(e -> {
            searchBook();
        });
        btnNewButton.setBounds(481, 4, 73, 27);
        panel_1.add(btnNewButton);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"전체", "ID", "제목", "저자", "출판사", "전공 여부"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(396, 8, 83, 21);
        panel_1.add(comboBox);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(93, 83, 554, 1);
        panel.add(separator);

        // ScrollPane 1
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 37, 554, 349);
        panel_1.add(scrollPane);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "제목", "저자", "출판사", "대출 여부", "전공 여부"}
        ));
        scrollPane.setViewportView(table_1);
        
        JButton btnNewButton_3 = new JButton("엑셀 저장");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    bookExportToExcel();
        	}
        });
        btnNewButton_3.setFont(new Font("여기어때 잘난체", Font.PLAIN, 11));
        btnNewButton_3.setBackground(SystemColor.info);
        btnNewButton_3.setBounds(451, 394, 91, 23);
        panel_1.add(btnNewButton_3);
        
        
        JButton btnNewButton_3_1 = new JButton("데이터 불러오기");
        btnNewButton_3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("엑셀 파일 선택");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel 파일", "xlsx", "xls"));
                
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
						loadBookDataFromExcel(filePath);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

                }
            }
        });
        btnNewButton_3_1.setFont(new Font("여기어때 잘난체", Font.PLAIN, 11));
        btnNewButton_3_1.setBackground(SystemColor.info);
        btnNewButton_3_1.setBounds(316, 394, 123, 23);
        panel_1.add(btnNewButton_3_1);

        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(93, 94, 554, 396);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("제목");
        lblNewLabel_2.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_2.setBounds(130, 104, 50, 15);
        panel_2.add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(192, 103, 217, 21);
        panel_2.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("저자");
        lblNewLabel_2_1.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_2_1.setBounds(130, 149, 50, 15);
        panel_2.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_1_1 = new JLabel("출판사");
        lblNewLabel_2_1_1.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_2_1_1.setBounds(130, 195, 50, 15);
        panel_2.add(lblNewLabel_2_1_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(192, 148, 217, 21);
        panel_2.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(192, 194, 217, 21);
        panel_2.add(textField_3);

        JButton btnNewButton_1 = new JButton("등록");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerBook();
            }
        });

        btnNewButton_1.setBounds(439, 344, 91, 23);
        panel_2.add(btnNewButton_1);

        JButton btnNewButton_1_1 = new JButton("뒤로가기");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_2.setVisible(false);
                panel_1.setVisible(true);

            }
        });
        btnNewButton_1_1.setBounds(41, 344, 91, 23);
        panel_2.add(btnNewButton_1_1);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("전공");
        lblNewLabel_2_1_1_1.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_2_1_1_1.setBounds(130, 237, 50, 15);
        panel_2.add(lblNewLabel_2_1_1_1);
        
        comboBox_3 = new JComboBox<>();
        comboBox_3.setModel(new DefaultComboBoxModel<>(new String[]{"전공", "비전공"}));
        comboBox_3.setBounds(192, 235, 217, 23);
        panel_2.add(comboBox_3);

        panel_2.setVisible(false);

        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_2.setVisible(true);
                panel_1.setVisible(false);
                panel_3.setVisible(false);
                panel_4.setVisible(false);
                panel_5.setVisible(false);
                panel_6.setVisible(false);
                panel_7.setVisible(false);




            }
        });

        lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_1.setVisible(true);
                panel_2.setVisible(false);
                panel_3.setVisible(false);
                panel_4.setVisible(false);
                panel_5.setVisible(false);
                panel_6.setVisible(false);
                panel_7.setVisible(false);


            }
        });

        panel_3.setVisible(false);

        lblNewLabel_1_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_3.setVisible(true);
                panel_1.setVisible(false);
                panel_2.setVisible(false);
                panel_4.setVisible(false);
                panel_5.setVisible(false);
                panel_6.setVisible(false);
                panel_7.setVisible(false);
            }
        });

        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(93, 94, 554, 396);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(panel_3);
        panel_3.setLayout(null);

        panel_4.setBackground(Color.WHITE);
        panel_4.setBounds(93, 94, 554, 396);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel("이름");
        lblNewLabel_3.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_3.setBounds(158, 107, 50, 15);
        panel_4.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("전화번호");
        lblNewLabel_4.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_4.setBounds(139, 156, 67, 15);
        panel_4.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("소속");
        lblNewLabel_4_1.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_4_1.setBounds(158, 205, 67, 15);
        panel_4.add(lblNewLabel_4_1);
        
        JLabel lblNewLabel_4_1_1 = new JLabel("성별");
        lblNewLabel_4_1_1.setFont(new Font("나눔바른펜", Font.BOLD, 17));
        lblNewLabel_4_1_1.setBounds(158, 258, 67, 15);
        panel_4.add(lblNewLabel_4_1_1);
        
        comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[]{"남자", "여자"}));
        comboBox_1.setBounds(232, 254, 115, 23);
        panel_4.add(comboBox_1);
        
        textField_4 = new JTextField();
        textField_4.setBounds(232, 202, 115, 21);
        panel_4.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(232, 153, 115, 21);
        panel_4.add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(232, 104, 115, 21);
        panel_4.add(textField_6);
        
        JButton btnNewButton_2 = new JButton("뒤로가기");
        btnNewButton_2.setBounds(28, 341, 91, 23);
        panel_4.add(btnNewButton_2);
        
        JButton btnNewButton_2_1 = new JButton("등록");
        btnNewButton_2_1.setBounds(426, 341, 91, 23);
        panel_4.add(btnNewButton_2_1);
        
        btnNewButton_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerFriend();
            }
        });
        
       
        


        panel_4.setVisible(false);
        
        lblNewLabel_1_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	panel_4.setVisible(true);
                panel_3.setVisible(false);
                panel_1.setVisible(false);
                panel_2.setVisible(false);
                panel_5.setVisible(false);
                panel_6.setVisible(false);
                panel_7.setVisible(false);
            }
        });
        
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(93, 94, 554, 417);
        panel.add(panel_5);
        panel_5.setLayout(null);

        textField_7 = new JTextField();
        textField_7.setBounds(0, 8, 398, 21);
        panel_5.add(textField_7);
        textField_7.setColumns(10);

        JButton btnFriendButton = new JButton("검색");
        btnFriendButton.setBackground(new Color(245, 245, 245));
        btnFriendButton.setFont(new Font("카페24 심플해", Font.BOLD, 17));
        btnFriendButton.addActionListener(e -> {
            searchLoan();
        });
        btnFriendButton.setBounds(481, 4, 73, 27);
        panel_5.add(btnFriendButton);
        
        comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[]{"도서명", "대출자", "대출일", "반납일", "반납 예정일"}));
        comboBox_2.setToolTipText("");
        comboBox_2.setBounds(396, 8, 83, 21);
        panel_5.add(comboBox_2);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 37, 554, 349);
        panel_5.add(scrollPane_2);

        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[] {"책 아이디", "친구 아이디", "책 제목", "대출자", "대출 일자", "반납 예정일", "반납 일자"}
        ));
        scrollPane_2.setViewportView(table_2);
        
        JButton btnNewButton_4 = new JButton("엑셀 저장");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loanExportToExcel();
        	}
        });
        btnNewButton_4.setBackground(SystemColor.info);
        btnNewButton_4.setFont(new Font("여기어때 잘난체", Font.BOLD, 12));
        btnNewButton_4.setBounds(463, 384, 91, 33);
        panel_5.add(btnNewButton_4);
        
        JButton btnNewButton_4_1 = new JButton("데이터 불러오기");
        btnNewButton_4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("엑셀 파일 선택");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel 파일", "xlsx", "xls"));
                
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
						loadLoanDataFromExcel(filePath);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

                }
            }
        });
        btnNewButton_4_1.setFont(new Font("여기어때 잘난체", Font.PLAIN, 11));
        btnNewButton_4_1.setBackground(SystemColor.info);
        btnNewButton_4_1.setBounds(316, 394, 123, 23);
        panel_5.add(btnNewButton_4_1);
        
        
        
        panel_6.setBackground(Color.WHITE);
        panel_6.setBounds(93, 94, 554, 417);
        panel.add(panel_6);
        panel_6.setLayout(null);

        textField_8 = new JTextField();
        textField_8.setBounds(0, 8, 398, 21);
        panel_6.add(textField_8);
        textField_8.setColumns(10);

        JButton btnFriendSearchButton = new JButton("검색");
        btnFriendSearchButton.setBackground(new Color(245, 245, 245));
        btnFriendSearchButton.setFont(new Font("카페24 심플해", Font.BOLD, 17));
        comboBox_4 = new JComboBox<>();
        comboBox_4.setModel(new DefaultComboBoxModel<>(new String[]{"전체", "ID", "이름", "전화번호", "소속"}));
        comboBox_4.setToolTipText("");
        comboBox_4.setBounds(396, 8, 83, 21);
        panel_6.add(comboBox_4);

        btnFriendSearchButton.addActionListener(e -> {
            ArrayList<Friend> friends = new ArrayList<>();
            friends = searchFriends(textField_8.getText(), (String)comboBox_4.getSelectedItem());
            updateFriendTable(friends);
        });
        btnFriendSearchButton.setBounds(481, 4, 73, 27);
        panel_6.add(btnFriendSearchButton);
        
        JButton btnNewButton_5_1 = new JButton("데이터 불러오기");
        btnNewButton_5_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("엑셀 파일 선택");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel 파일", "xlsx", "xls"));
                
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
						loadFriendDataFromExcel(filePath);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

                }
            }
        });
        btnNewButton_5_1.setFont(new Font("여기어때 잘난체", Font.PLAIN, 11));
        btnNewButton_5_1.setBackground(SystemColor.info);
        btnNewButton_5_1.setBounds(316, 394, 123, 23);
        panel_6.add(btnNewButton_5_1);

        
        
        


        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(0, 37, 554, 349);
        panel_6.add(scrollPane_3);

        table_3 = new JTable();
        table_3.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "이름", "전화번호", "소속", "성별"}
        ));
        scrollPane_3.setViewportView(table_3);
        
        JButton btnNewButton_5 = new JButton("엑셀 저장");
        btnNewButton_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		friendExportToExcel();
        	}
        });
        btnNewButton_5.setBackground(SystemColor.info);
        btnNewButton_5.setFont(new Font("여기어때 잘난체", Font.BOLD, 12));
        btnNewButton_5.setBounds(463, 384, 91, 33);
       
        panel_6.add(btnNewButton_5);
        
        panel_6.setVisible(false);

        lblNewLabel_1_1_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	panel_6.setVisible(true);
            	panel_5.setVisible(false);
            	panel_4.setVisible(false);
                panel_3.setVisible(false);
                panel_1.setVisible(false);
                panel_2.setVisible(false);
                panel_7.setVisible(false);

            }
        });
        
        panel_5.setVisible(false);
        lblNewLabel_1_1_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	panel_5.setVisible(true);
            	panel_4.setVisible(false);
                panel_3.setVisible(false);
                panel_1.setVisible(false);
                panel_2.setVisible(false);
            	panel_6.setVisible(false);
            	panel_7.setVisible(false);



            }
        });
        
        panel_7.setBackground(Color.WHITE);
        panel_7.setBounds(93, 94, 554, 396);
        panel.add(panel_7);
        panel_7.setLayout(null);

        

        loadBooks(); // 프로그램 시작 시 전체 도서 목록 로드
        loadFriends();
        loadLoans();

               

        
        

        
    }
    private void loadBookDataFromExcel(String filePath) throws SQLException {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트를 읽습니다.
            DataFormatter dataFormatter = new DataFormatter();
            
            boolean firstRowSkipped = false;
           

            
            for (Row row : sheet) {
            	 if (!firstRowSkipped) {
                     firstRowSkipped = true;
                     continue; // 첫 번째 행은 건너뜁니다.
                 }
                // 열의 데이터를 읽고 데이터베이스에 삽입합니다.
                String ID = dataFormatter.formatCellValue(row.getCell(0));
                String title = dataFormatter.formatCellValue(row.getCell(1));
                String author = dataFormatter.formatCellValue(row.getCell(2));
                String publisher = dataFormatter.formatCellValue(row.getCell(3));
                String type = dataFormatter.formatCellValue(row.getCell(4));
                String status = dataFormatter.formatCellValue(row.getCell(5));
                
                int id = Integer.parseInt(ID);

                // 필요한 만큼 열을 읽어옵니다.
                
                insertIntoDatabase(id, title, author, publisher, type, status);
            }
            
            workbook.close();
            inputStream.close();
            
            JOptionPane.showMessageDialog(null, "데이터가 성공적으로 불러와졌습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터를 불러오는 중 오류가 발생했습니다.");
        }
    }
    
    private boolean isIdExists(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExists = false;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
            String sql = "SELECT id FROM books WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isExists = true;
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
        
        return isExists;
    }
   

  private void insertIntoDatabase(int id, String title, String author, String publisher, String type, String status) throws SQLException {
    String typeText = type.equals("대출 가능") ? "0" : "1";
    String statusText = status.equals("비전공") ? "0" : "1";
    if (!isIdExists(id)) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (id, title, author, publisher, type, status) VALUES (?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, publisher);
            pstmt.setString(5, typeText);
            pstmt.setString(6, statusText);
            pstmt.executeUpdate();
        }
        loadBooks();
    }
}

private void loadFriendDataFromExcel(String filePath) throws SQLException {
    try {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        
        Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트를 읽습니다.
        DataFormatter dataFormatter = new DataFormatter();
        
        boolean firstRowSkipped = false;
       

        
        for (Row row : sheet) {
        	 if (!firstRowSkipped) {
                 firstRowSkipped = true;
                 continue; // 첫 번째 행은 건너뜁니다.
             }
            // 열의 데이터를 읽고 데이터베이스에 삽입합니다.
            String ID = dataFormatter.formatCellValue(row.getCell(0));
            String name = dataFormatter.formatCellValue(row.getCell(1));
            String phone = dataFormatter.formatCellValue(row.getCell(2));
            String school = dataFormatter.formatCellValue(row.getCell(3));
            String type = dataFormatter.formatCellValue(row.getCell(4));
            
            int id = Integer.parseInt(ID);

            // 필요한 만큼 열을 읽어옵니다.
            
            insertFriendIntoDatabase(id, name, phone, school, type);
        }
        
        workbook.close();
        inputStream.close();
        
        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 불러와졌습니다.");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "데이터를 불러오는 중 오류가 발생했습니다.");
    }
}

private boolean isFriendIdExists(int id) throws SQLException {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    boolean isExists = false;
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
        String sql = "SELECT id FROM friends WHERE id = ?";
        statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            isExists = true;
        }
    } finally {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (conn != null) conn.close();
    }
    
    return isExists;
}
private void insertFriendIntoDatabase(int id, String name, String phone, String school, String type) throws SQLException {
    String statusText = type.equals("남자") ? "1" : "0";
    if (!isFriendIdExists(id)) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO friends (id, name, phone, school, type) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, school);
            pstmt.setString(5, statusText);
            pstmt.executeUpdate();
        }
        loadFriends();
    }
}

private void loadLoanDataFromExcel(String filePath) throws SQLException {
    try {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        
        Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트를 읽습니다.
        DataFormatter dataFormatter = new DataFormatter();
        
        boolean firstRowSkipped = false;
       

        
        for (Row row : sheet) {
        	 if (!firstRowSkipped) {
                 firstRowSkipped = true;
                 continue; // 첫 번째 행은 건너뜁니다.
             }
            // 열의 데이터를 읽고 데이터베이스에 삽입합니다.
            String bookID = dataFormatter.formatCellValue(row.getCell(0));
            String friendID = dataFormatter.formatCellValue(row.getCell(1));
            String title = dataFormatter.formatCellValue(row.getCell(2));
            String name = dataFormatter.formatCellValue(row.getCell(3));
            String loanDate = dataFormatter.formatCellValue(row.getCell(4));
            String dueDate = dataFormatter.formatCellValue(row.getCell(5));
            String returnDate = dataFormatter.formatCellValue(row.getCell(6));
            
            int book_id = Integer.parseInt(bookID);
            int friend_id = Integer.parseInt(friendID);


            // 필요한 만큼 열을 읽어옵니다.
            
            insertLoanIntoDatabase(book_id, friend_id, title, name, loanDate, dueDate, returnDate);
        }
        
        workbook.close();
        inputStream.close();
        
        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 불러와졌습니다.");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "데이터를 불러오는 중 오류가 발생했습니다.");
    }
}

private boolean isLoanIdExists(int book_id, int friend_id) throws SQLException {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    boolean isExists = false;
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
        String sql = "SELECT book_id, friend_id FROM loans WHERE book_id = ? AND friend_id = ?";
        statement = conn.prepareStatement(sql);
        statement.setInt(1, book_id);
        statement.setInt(2, friend_id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            isExists = true;
        }
    } finally {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (conn != null) conn.close();
    }
    
    return isExists;
}

private void insertLoanIntoDatabase(int book_id, int friend_id, String title, String name, String loanDate, String dueDate, String returnDate) throws SQLException {
    if (!isLoanIdExists(book_id, friend_id)) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO loans (book_id, friend_id, title, name, loanDate, dueDate, returnDate) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, book_id);
            pstmt.setInt(2, friend_id);
            pstmt.setString(3, title);
            pstmt.setString(4, name);
            pstmt.setString(5, loanDate);
            pstmt.setString(6, dueDate);
            pstmt.setString(7, returnDate);

            pstmt.executeUpdate();
        }
        loadLoans();
    }
}

private void bookExportToExcel() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Book List");
            Row headerRow = sheet.createRow(0);
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(model.getValueAt(i, j)));
                }
            }
            FileOutputStream outputStream = new FileOutputStream("BookList.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            JOptionPane.showMessageDialog(null, "엑셀 파일로 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "엑셀 파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   private void loanExportToExcel() {
    try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Loan List");
        Row headerRow = sheet.createRow(0);
        DefaultTableModel model = (DefaultTableModel) table_2.getModel();

        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                Object value = model.getValueAt(i, j);
                if (value != null && value.toString().equals("반납하기")) {
                    // "반납하기" 값을 공란으로 변경
                    cell.setCellValue("");
                } else {
                    // 다른 값은 그대로 저장
                    cell.setCellValue(String.valueOf(value));
                }
            }
        }
        FileOutputStream outputStream = new FileOutputStream("loanList.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        JOptionPane.showMessageDialog(null, "엑셀 파일로 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "엑셀 파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void friendExportToExcel() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Friend List");
            Row headerRow = sheet.createRow(0);
            DefaultTableModel model = (DefaultTableModel) table_3.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(model.getValueAt(i, j)));
                }
            }
            FileOutputStream outputStream = new FileOutputStream("friendList.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            JOptionPane.showMessageDialog(null, "엑셀 파일로 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "엑셀 파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void registerBook() {
        int response = JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "도서 등록 확인", JOptionPane.YES_NO_OPTION);

        if (response != JOptionPane.YES_OPTION) {
            return;
        }

        String title = textField_1.getText();
        String author = textField_2.getText();
        String publisher = textField_3.getText();

        // 전공 여부 콤보 박스에서 선택된 값을 가져옴
        String majorStatus = comboBox_3.getSelectedItem().equals("전공") ? "1" : "0";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (title, author, publisher, type) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setString(4, majorStatus);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        loadBooks();

        JOptionPane.showMessageDialog(null, "도서가 등록되었습니다.");
    }

    private void registerFriend() {
        int response = JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "친구 등록 확인", JOptionPane.YES_NO_OPTION);

        if (response != JOptionPane.YES_OPTION) {
            return;
        }

        String name = textField_6.getText();
        String phone = textField_5.getText();
        String school = textField_4.getText();

		// 전공 여부 콤보 박스에서 선택된 값을 가져옴
        String type = comboBox_1.getSelectedItem().equals("남자") ? "1" : "0";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO friends (name, phone, school, type) VALUES (?, ?, ?, ?)")) {
			pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, school);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        loadFriends();

        JOptionPane.showMessageDialog(null, "친구가 등록되었습니다.");
    }
    
    private void loadFriends() {
        friendList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM friends")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String school = rs.getString("school");
                String type = rs.getString("type");


                Friend friend = new Friend(id, name, phone, school, type);
                friendList.add(friend);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        updateFriendTable(friendList);
    }



    private void loadBooks() {
        bookList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String status = rs.getString("status");
                String majorStatus = rs.getString("type");


                Book book = new Book(id, title, author, publisher, status, majorStatus);
                bookList.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        updateBookTable(bookList);
    }
    
    private void loadLoans() {
        loanList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT loans.dueDate, loans.book_id, loans.friend_id, loans.loanDate, friends.name, books.title, loans.returnDate FROM loans JOIN friends ON loans.friend_id = friends.id JOIN books ON loans.book_id = books.id");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	int book_id = rs.getInt("book_id");
            	int friend_id = rs.getInt("friend_id");
            	String loanDate = rs.getString("loanDate").substring(0, 10);
                String friendName = rs.getString("name");
                String bookTitle = rs.getString("title");
                String returnDate = rs.getString("returnDate");
                String dueDate = rs.getString("dueDate").substring(0, 10);
                
                Loan loan = new Loan(book_id, friend_id, loanDate, friendName, bookTitle, returnDate, dueDate);
                loanList.add(loan);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        updateLoanTable(loanList);
    }

    private void searchBook() {
	    String searchType = (String) comboBox.getSelectedItem();
	    String searchText = textField.getText().trim();
	    ArrayList<Book> searchResult = new ArrayList<>();

    String query = "";
    switch (searchType) {
        case "전체":
            query = "SELECT * FROM books WHERE id = ? OR title LIKE ? OR author LIKE ? OR publisher LIKE ?";
            break;
        case "ID":
            query = "SELECT * FROM books WHERE id = ?";
            break;
        case "제목":
            query = "SELECT * FROM books WHERE title LIKE ?";
            break;
        case "저자":
            query = "SELECT * FROM books WHERE author LIKE ?";
            break;
        case "출판사":
            query = "SELECT * FROM books WHERE publisher LIKE ?";
            break;
        case "전공 여부":
            query = "SELECT * FROM books WHERE `type` LIKE ?";
            break;
    }

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        if (searchType.equals("전체")) {
            pstmt.setString(1, searchText);
            for (int i = 1; i <= 4; i++) {
                pstmt.setString(i, "%" + searchText + "%");
            }
        } else if (searchType.equals("ID")) {
            pstmt.setInt(1, Integer.parseInt(searchText));
        } else {
            pstmt.setString(1, "%" + searchText + "%");
        }

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            String status = rs.getString("status");
            String majorStatus = rs.getString("type");

            Book book = new Book(id, title, author, publisher, status, majorStatus);
            searchResult.add(book);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    updateBookTable(searchResult);
}
    private void searchLoan() {
        String searchType = (String) comboBox_2.getSelectedItem();
        String searchText = textField_7.getText().trim();
        ArrayList<Loan> searchResult = new ArrayList<>();

        String query = "SELECT loans.dueDate, loans.book_id, loans.friend_id, loans.loanDate, friends.name, friends.phone, friends.school, books.title, loans.returnDate " +
                       "FROM loans " +
                       "JOIN friends ON loans.friend_id = friends.id " +
                       "JOIN books ON loans.book_id = books.id ";

        // WHERE 절을 searchType에 따라 추가
        if (!searchText.isEmpty()) {
            switch (searchType) {
                case "도서명":
                    query += "WHERE books.title LIKE ?";
                    break;
                case "대출자":
                    query += "WHERE friends.name LIKE ?";
                    break;
                case "대출일":
                    query += "WHERE loans.loanDate LIKE ?";
                    break;
                case "반납일":
                    query += "WHERE loans.returnDate LIKE ?";
                    break;
                case "반납 예정일":
                    query += "WHERE loans.dueDate LIKE ?";
                    break;
                default:
                    break;
            }
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // 매개변수 설정
            if (!searchText.isEmpty()) {
                pstmt.setString(1, "%" + searchText + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int book_id = rs.getInt("book_id");
                int friend_id = rs.getInt("friend_id");
                String loanDate = rs.getString("loanDate").substring(0, 10);
                String friendName = rs.getString("name");
                String bookTitle = rs.getString("title");
                String returnDate = rs.getString("returnDate");
                String dueDate = rs.getString("dueDate").substring(0, 10);


                // Loan 객체를 생성하여 결과 리스트에 추가
                Loan loan = new Loan(book_id, friend_id, loanDate, friendName, bookTitle, returnDate, dueDate);
                searchResult.add(loan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // 결과 업데이트
        updateLoanTable(searchResult);
    }

 // 테이블 업데이트 메소드
    private void updateBookTable(ArrayList<Book> bookList) {
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0);

        for (Book book : bookList) {
            String statusText = book.getStatus().equals("0") ? "대출 가능" : "대출 불가";
            String typeText = book.getMajorStatus().equals("0") ? "비전공" : "전공";
            model.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(), statusText, typeText});
        }

        // 도서 제목을 클릭했을 때의 이벤트 처리
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // 한 번 클릭된 경우
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if (column == 1) { // 제목 열 클릭 시
                        int selectedId = (int) target.getValueAt(row, 0); // 선택된 도서의 ID를 가져옵니다.
                    	Book selectedBook = getBookById(selectedId); // 도서 ID를 사용하여 해당 도서 객체를 가져옵니다.
                    	showBookDetails(selectedBook, selectedId); // 수정된 도서 객체를 사용하여 상세 정보를 보여줍니다.

                    }
                }
            }
        });
    }
    
    private void updateFriendTable(ArrayList<Friend> friendList) {
        DefaultTableModel model = (DefaultTableModel) table_3.getModel();
        model.setRowCount(0);

        for (Friend friend : friendList) {
            String statusText = friend.getType().equals("1") ? "남" : "여";
            model.addRow(new Object[]{friend.getId(), friend.getName(), friend.getPhone(), friend.getSchool(), statusText});
        }

        // 도서 제목을 클릭했을 때의 이벤트 처리
        table_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // 한 번 클릭된 경우
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if (column == 1) { // 제목 열 클릭 시
                        int selectedId = (int) target.getValueAt(row, 0); // 선택된 도서의 ID를 가져옵니다.
                    	Friend selectedFriend = getFriendById(selectedId); // 도서 ID를 사용하여 해당 도서 객체를 가져옵니다.
                    	showFriendDetails(selectedFriend); // 수정된 도서 객체를 사용하여 상세 정보를 보여줍니다.

                    }
                }
            }
        });
    }
    
    private void updateLoanTable(ArrayList<Loan> loanList) {
    DefaultTableModel model = (DefaultTableModel) table_2.getModel();
    model.setRowCount(0);

    for (Loan loan : loanList) {
        String returnStatus = (loan.getReturnDate() == null) ? "반납하기" : loan.getReturnDate().substring(0, 10);
        // 가져온 데이터를 테이블에 추가
        model.addRow(new Object[]{loan.getBook_id(), loan.getFriend_id(), loan.getBookTitle(), loan.getFriendName(), loan.getLoanDate(),loan.getDueDate(), returnStatus});
    }


    int columnIndex = 6;
    

    table_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table_2.rowAtPoint(e.getPoint());
            int column = table_2.columnAtPoint(e.getPoint());
            if (column == 2) {
                int book_id = (int) table_2.getValueAt(row, 0);
                showBookDetailsPanel(book_id);
            } else if (column == 3){ 
            	 int friend_id = (int) table_2.getValueAt(row, 1);
                 showFriendDetailsPanel(friend_id);
            	
            } else {
            if(table_2.getValueAt(row, columnIndex).equals("반납하기")) {
	            if (column == columnIndex) { // 해당 행의 이름 열 클릭 시
	                String bookName = (String) table_2.getValueAt(row, 2);
	                int option = JOptionPane.showConfirmDialog(null, bookName + "을(를) 반납하시겠습니까?", "반납 확인", JOptionPane.YES_NO_OPTION);
	                if (option == JOptionPane.YES_OPTION) {
	                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "")) {
	                        // 해당 행의 데이터 가져오기
	                    	int bookId = (int) table_2.getValueAt(row, 0); // 책 id 열
	                        int friendId = (int) table_2.getValueAt(row, 1); // 친구 id 열
	
	                        // 대출 정보 삭제
	                        PreparedStatement updatePstmt = conn.prepareStatement("UPDATE loans SET returnDate = ? WHERE book_id = ? AND friend_id = ?");
	                        // 현재 날짜를 가져오기
	                        java.util.Date currentDate = new java.util.Date();
	                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                        String formattedDate = dateFormat.format(currentDate);
	                        java.sql.Date returnDate = java.sql.Date.valueOf(formattedDate);
	
	                        updatePstmt.setDate(1, returnDate);
	                        updatePstmt.setInt(2, bookId);
	                        updatePstmt.setInt(3, friendId);
	                        updatePstmt.executeUpdate();
	
	                        // 책 상태 변경
	                        PreparedStatement updateBookPstmt = conn.prepareStatement("UPDATE books SET status = 0 WHERE id = ?");
	                        updateBookPstmt.setInt(1, bookId);
	                        updateBookPstmt.executeUpdate();
	
	                        // 테이블에 반납 일자 업데이트
	                        table_2.setValueAt(returnDate, row, 6); // 2는 반납 일자 열의 인덱스
	                        loadBooks();
	
	                        JOptionPane.showMessageDialog(null, "반납되었습니다.", "반납 완료", JOptionPane.INFORMATION_MESSAGE);
	                    } catch (SQLException ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            }
	        }
        }
        }
    });
    }
    
    private void showBookDetailsPanel(int bookId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "")) {
            String sql = "SELECT title, author, publisher, type " +
                         "FROM books " +
                         "WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String bookTitle = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");
                String status = (type.equals("1")) ? "전공" : "비전공";
                
                JPanel bookDetailsPanel = createBookDetailsPanel(bookTitle, author, publisher, status);
                showPanelInFrame(bookDetailsPanel, "책 세부 정보");
            } else {
                JOptionPane.showMessageDialog(null, "해당하는 책이 없습니다.", "책 세부 정보", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createBookDetailsPanel(String bookTitle, String author, String publisher, String status) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("책 세부 정보"));
        panel.setBackground(Color.WHITE);
        panel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel titleLabel = new JLabel("제목:");
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(titleLabel);
        panel.add(new JLabel(bookTitle));

        JLabel authorLabel = new JLabel("저자:");
        authorLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(authorLabel);
        panel.add(new JLabel(author));

        JLabel publisherLabel = new JLabel("출판사:");
        publisherLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(publisherLabel);
        panel.add(new JLabel(publisher));

        JLabel typeLabel = new JLabel("전공 여부:");
        typeLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(typeLabel);
        panel.add(new JLabel(status));
        panel.setPreferredSize(new Dimension(500, 250));


        return panel;
    }

    private void showFriendDetailsPanel(int friendId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "")) {
            String sql = "SELECT name, phone, school, type " +
                         "FROM friends " +
                         "WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, friendId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String friendName = rs.getString("name");
                String friendPhone = rs.getString("phone");
                String friendSchool = rs.getString("school");
                String type = rs.getString("type");
                String status = (type.equals("1")) ? "남자" : "여자";
                
                JPanel friendDetailsPanel = createFriendDetailsPanel(friendName, friendPhone, friendSchool, status);
                showPanelInFrame(friendDetailsPanel, "대출자 세부 정보");
            } else {
                JOptionPane.showMessageDialog(null, "해당하는 대출자가 없습니다.", "대출자 세부 정보", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private JPanel createFriendDetailsPanel(String friendName, String friendPhone, String friendSchool, String status) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("대출자 세부 정보"));
        panel.setBackground(Color.WHITE);
        panel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(nameLabel);
        panel.add(new JLabel(friendName));

        JLabel phoneLabel = new JLabel("전화번호:");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(phoneLabel);
        panel.add(new JLabel(friendPhone));

        JLabel schoolLabel = new JLabel("소속:");
        schoolLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(schoolLabel);
        panel.add(new JLabel(friendSchool));

        JLabel sexLabel = new JLabel("성별:");
        sexLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(sexLabel);
        panel.add(new JLabel(status));
        panel.setPreferredSize(new Dimension(400, 250));


        return panel;
    }
    private void showPanelInFrame(JPanel panel, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    

    private Book getBookById(int id) {
        Book book = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String status = rs.getString("status");
                String majorStatus = rs.getString("type");


                book = new Book(id, title, author, publisher, status, majorStatus);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return book;
    }
    
    private Friend getFriendById(int id) {
        Friend friend = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM friends WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String school = rs.getString("school");
                String type = rs.getString("type");


                friend = new Friend(id, name, phone, school, type);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return friend;
    }

    
 // 선택된 도서의 상세 정보를 표시하는 메소드
    private void showBookDetails(Book book, int id) {
    	String majorStatus = null;
        // 패널 위치 조정
        panel_1.setVisible(false);
        panel_2.setVisible(false);
        panel_3.setVisible(false);
        panel_4.setVisible(true);
        
        // 패널_4 초기화
        panel_4.removeAll();
        
        // 테이블 생성
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("항목");
        model.addColumn("정보");
        
        // 각 정보 추가
        model.addRow(new Object[]{"ID", book.getId()});
        model.addRow(new Object[]{"제목", book.getTitle()});
        model.addRow(new Object[]{"저자", book.getAuthor()});
        model.addRow(new Object[]{"출판사", book.getPublisher()});
        if( book.getMajorStatus().equals("1")) {
           majorStatus = "전공";
        }
        else {
        	majorStatus = "비전공";
        }
        model.addRow(new Object[]{"전공", majorStatus});

        
        // 대출 가능 여부 표시
        String loanStatus = book.getStatus().equals("1") ? "대출 불가" : "대출 가능";
        JButton loanButton = new JButton(loanStatus);
        loanButton.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        loanButton.setBounds(20, 300, 150, 30);
        if( book.getStatus().equals("1") ) {
        	loanButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 친구를 검색하는 패널을 띄움
                    JOptionPane.showMessageDialog(null, "이미 대출된 책입니다.");

                }
                
            });
        }
        else {
        	 loanButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // 친구를 검색하는 패널을 띄움
                     showFriendSearchPanel(id);
                 }
             });
        }
       

        panel_4.add(loanButton);
        
        // 도서 수정 버튼 추가
        JButton editButton = new JButton("도서 수정");
        editButton.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        editButton.setBounds(190, 300, 150, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정할 도서 정보를 가져오기
                int selectedId = book.getId();
                String selectedTitle = book.getTitle();
                String selectedAuthor = book.getAuthor();
                String selectedPublisher = book.getPublisher();

                // 수정할 도서 정보를 사용하여 수정 대화상자 열기
                JFrame editFrame = new JFrame("도서 수정");
                editFrame.setSize(400, 300);
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel editPanel = new JPanel();
                editFrame.getContentPane().add(editPanel);
                editPanel.setLayout(null);

                JLabel titleLabel = new JLabel("제목:");
                titleLabel.setBounds(30, 30, 80, 25);
                editPanel.add(titleLabel);

                JTextField titleTextField = new JTextField(selectedTitle);
                titleTextField.setBounds(100, 30, 250, 25);
                editPanel.add(titleTextField);

                JLabel authorLabel = new JLabel("저자:");
                authorLabel.setBounds(30, 80, 80, 25);
                editPanel.add(authorLabel);

                JTextField authorTextField = new JTextField(selectedAuthor);
                authorTextField.setBounds(100, 80, 250, 25);
                editPanel.add(authorTextField);

                JLabel publisherLabel = new JLabel("출판사:");
                publisherLabel.setBounds(30, 130, 80, 25);
                editPanel.add(publisherLabel);

                JTextField publisherTextField = new JTextField(selectedPublisher);
                publisherTextField.setBounds(100, 130, 250, 25);
                editPanel.add(publisherTextField);

                JButton saveButton = new JButton("저장");
                saveButton.setBounds(100, 180, 100, 25);
                editPanel.add(saveButton);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 수정된 도서 정보 가져오기
                        String updatedTitle = titleTextField.getText();
                        String updatedAuthor = authorTextField.getText();
                        String updatedPublisher = publisherTextField.getText();

                        // 수정된 도서 정보를 적용하여 도서 정보 업데이트
                        for (Book book : bookList) {
                            if (book.getId() == selectedId) {
                                book.setTitle(updatedTitle);
                                book.setAuthor(updatedAuthor);
                                book.setPublisher(updatedPublisher);
                                // 파일에 수정된 정보 저장
                                updateBook(bookList);
                                break;
                            }
                        }

                        // 수정 후 창 닫기
                        editFrame.dispose();

                        // 수정된 정보로 도서 목록 다시 불러오기
                        updateBookTable(bookList);
                        panel_4.setVisible(false);
                        panel_1.setVisible(true);
                    }
                });

                editFrame.setVisible(true);
            }
        });


        panel_4.add(editButton);
        
        // 도서 삭제 버튼 추가
        JButton deleteButton = new JButton("도서 삭제");
        deleteButton.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        deleteButton.setBounds(360, 300, 150, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    // 도서 삭제 메소드 호출
                    deleteBook(book.getId()); // ISBN 번호를 인자로 전달하여 도서 삭제
                    JOptionPane.showMessageDialog(null, "도서가 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    loadBooks();
                    // 삭제 후 패널을 닫거나 다른 처리를 수행할 수 있음
                    panel_4.setVisible(false);
                    panel_1.setVisible(true);
                }
            }
        });

        panel_4.add(deleteButton);
        
        // 테이블 생성 및 설정
        JTable table_2 = new JTable(model);
        JScrollPane scrollPane_2 = new JScrollPane(table_2);
        scrollPane_2.setBounds(20, 20, 480, 250);
        panel_4.add(scrollPane_2);
    }
    
 // 친구를 검색하는 패널을 보여주는 메서드
    private void showFriendSearchPanel(int selectedId) {
        // 패널 생성
        JPanel friendSearchPanel = new JPanel();
        friendSearchPanel.setLayout(null);

        // 검색 조건 입력 필드 및 버튼 생성
        JLabel searchLabel = new JLabel("친구 검색:");
        searchLabel.setBounds(20, 20, 80, 25);
        friendSearchPanel.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(110, 20, 200, 25);
        friendSearchPanel.add(searchField);

        JButton searchButton = new JButton("검색");
        searchButton.setBounds(320, 20, 80, 25);
        friendSearchPanel.add(searchButton);

        // 검색 조건 선택 콤보 박스 생성
        String[] searchOptions = {"전체", "ID", "이름", "전화번호", "소속"};
        JComboBox<String> comboBox = new JComboBox<>(searchOptions);
        comboBox.setBounds(20, 60, 100, 25);
        friendSearchPanel.add(comboBox);

        // 검색 결과를 표시할 테이블 생성
        DefaultTableModel friendTableModel = new DefaultTableModel();
        friendTableModel.addColumn("ID");
        friendTableModel.addColumn("이름");
        friendTableModel.addColumn("전화번호");
        friendTableModel.addColumn("소속");
        friendTableModel.addColumn("성별");

        JTable friendTable = new JTable(friendTableModel);
        JScrollPane friendScrollPane = new JScrollPane(friendTable);
        friendScrollPane.setBounds(20, 100, 380, 150);
        friendSearchPanel.add(friendScrollPane);

        // 검색 버튼 클릭 시 이벤트 처리
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 검색어 및 검색 조건 가져오기
                String searchText = searchField.getText();
                String searchType = (String) comboBox.getSelectedItem();

                // 친구 검색 메서드 호출
                List<Friend> searchResults = searchFriends(searchText, searchType);

                // 검색 결과를 테이블에 표시
                friendTableModel.setRowCount(0); // 테이블 초기화
                String type = null;
                for (Friend friend : searchResults) {
                    if (friend.getType() == "1") {
                        type = "남자";
                    } else {
                        type = "여자";
                    }
                    friendTableModel.addRow(new Object[]{friend.getId(), friend.getName(), friend.getPhone(), friend.getSchool(), type});
                }
            }
        });
        
     // 이름을 클릭했을 때 대출 여부를 묻는 다이얼로그를 표시하는 이벤트 처리
        friendTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = friendTable.rowAtPoint(e.getPoint());
                int column = friendTable.columnAtPoint(e.getPoint());
                if (row >= 0 && column == 0) { // 이름 열 클릭 시
                    int option = JOptionPane.showConfirmDialog(null, "반납 예정일을 설정하시겠습니까?", "반납 예정일 설정", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // 현재 날짜를 가져오기
                        java.util.Date currentDate = new java.util.Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String todayDateStr = dateFormat.format(currentDate);
                        
                        // 날짜 입력을 받기 위한 다이얼로그 표시
                        JTextField dateField = new JTextField(10);
                        JPanel panel = new JPanel(new GridLayout(0, 1));
                        panel.add(new JLabel("날짜를 입력하세요 (yyyy-MM-dd 형식)"));
                        panel.add(dateField);
                        int result = JOptionPane.showConfirmDialog(null, panel, "반납 예정일 입력", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // 사용자가 입력한 날짜를 가져옴
                            String dateString = dateField.getText();
                            int selectedMonth = Integer.parseInt(dateString.substring(5, 7));
                            int selectedDay = Integer.parseInt(dateString.substring(8, 10));


                            // SimpleDateFormat을 사용하여 날짜 형식을 파싱
                            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date selectedDate = inputDateFormat.parse(dateString);
                                
                                // 현재 날짜와 비교
                                if (selectedDate.before(currentDate)) {
                                    JOptionPane.showMessageDialog(null, "현재 날짜보다 이전 날짜는 선택할 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                                }
                                // 월이 12월을 넘어가는 경우
                                else if (selectedMonth > 12) {
                                    JOptionPane.showMessageDialog(null, "12월까지만 입력 가능합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                                }
                                // 31일을 초과하는 경우
                                else if (selectedDay > 31) {
                                    JOptionPane.showMessageDialog(null, "31일까지만 입력 가능합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                                }
                                // 사용자가 선택한 날짜를 반납 예정일로 설정
                                else {
                                    // 여기에 반납 예정일로 설정된 내용을 출력하거나, 데이터베이스에 업데이트하는 코드를 추가할 수 있습니다.
                                    JOptionPane.showMessageDialog(null, "반납 예정일로 설정되었습니다: " + inputDateFormat.format(selectedDate));
                                    int id = (int) friendTable.getValueAt(row, column);
                                    // DB의 상태 변경 메서드 호출
                                    updateLoanStatus(id, selectedId, selectedDate);
                                    loadBooks();
                                    loadLoans();
                                }
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(null, "잘못된 날짜 형식입니다. 다시 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }

            }
        });


        
        friendSearchPanel.setPreferredSize(new Dimension(420, 300));

        // 패널을 다이얼로그로 띄움
        JOptionPane.showMessageDialog(null, friendSearchPanel, "친구 검색", JOptionPane.PLAIN_MESSAGE);
    }
    public void updateLoanStatus(int friend_id, int bookId, Date dueDate) {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "")) {
        // Update book status
        try (PreparedStatement updateBookStmt = conn.prepareStatement("UPDATE books SET `status` = 1 WHERE id = ?")) {
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();
        }
        
        // Insert loan information
        try (PreparedStatement insertLoanStmt = conn.prepareStatement("INSERT INTO loans (book_id, friend_id, loanDate, dueDate) VALUES (?, ?, ?, ?)")) {
            insertLoanStmt.setInt(1, bookId);
            insertLoanStmt.setInt(2, friend_id);
            
            java.util.Date currentDate = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(currentDate);
            java.sql.Date loanDate = java.sql.Date.valueOf(formattedDate);


            // 이후에 loanDate 변수를 PreparedStatement에 설정하여 데이터베이스에 삽입하면 됩니다.
            insertLoanStmt.setDate(3, loanDate);
            insertLoanStmt.setTimestamp(4, new Timestamp(dueDate.getTime()));


            
            int rowsInserted = insertLoanStmt.executeUpdate();
            
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "대출되었습니다.", "대출 성공", JOptionPane.INFORMATION_MESSAGE);
                loadLoans();

            } else {
                JOptionPane.showMessageDialog(null, "대출에 실패했습니다.", "대출 실패", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    
    private ArrayList<Friend> searchFriends(String searchText, String searchType) {
        ArrayList<Friend> searchResult = new ArrayList<>();

        if (searchText == null || searchType == null) {
            // 예외 처리 또는 적절한 로그 처리
            return searchResult;
        }

        String query = "";
        switch (searchType) {
            case "전체":
                query = "SELECT * FROM friends WHERE id = ? OR `name` LIKE ? OR phone LIKE ? OR school LIKE ?";
                break;
            case "ID":
                query = "SELECT * FROM friends WHERE id = ?";
                break;
            case "이름":
                query = "SELECT * FROM friends WHERE `name` LIKE ?";
                break;
            case "전화번호":
                query = "SELECT * FROM friends WHERE phone LIKE ?";
                break;
            case "소속":
                query = "SELECT * FROM friends WHERE school LIKE ?";
                break;
            default:
                // 유효하지 않은 검색 유형 처리
                return searchResult;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/book", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (searchType.equals("전체")) {
                for (int i = 1; i <= 4; i++) {
                    pstmt.setString(i, "%" + searchText + "%");
                }
            } else if (searchType.equals("ID")) {
                pstmt.setInt(1, Integer.parseInt(searchText));
            } else {
                pstmt.setString(1, "%" + searchText + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String school = rs.getString("school");
                    String type = rs.getString("type");

                    Friend friend = new Friend(id, name, phone, school, type);
                    searchResult.add(friend);
                }
            }

        } catch (SQLException | NumberFormatException ex) {
            // SQLException 및 NumberFormatException 처리
            ex.printStackTrace(); // 또는 적절한 로그 처리
        }

        return searchResult;
    }

    
    private void showFriendDetails(Friend friend) {
    	String type = null;
        // 패널 위치 조정
        panel_1.setVisible(false);
        panel_2.setVisible(false);
        panel_3.setVisible(false);
        panel_4.setVisible(false);
        panel_5.setVisible(false);
        panel_6.setVisible(false);
        panel_7.setVisible(true);
        
        // 패널_4 초기화
        panel_7.removeAll();
        
        // 테이블 생성
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("항목");
        model.addColumn("정보");
        
        // 각 정보 추가
        model.addRow(new Object[]{"ID", friend.getId()});
        model.addRow(new Object[]{"이름", friend.getName()});
        model.addRow(new Object[]{"전화번호", friend.getPhone()});
        model.addRow(new Object[]{"소속", friend.getSchool()});
        if( friend.getType().equals("1")) {
           type = "남자";
        }
        else {
        	type = "여자";
        }
        model.addRow(new Object[]{"성별", type});

        
        // 도서 수정 버튼 추가
        JButton editFriendButton = new JButton("친구 정보 수정");
        editFriendButton.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        editFriendButton.setBounds(190, 300, 150, 30);
        editFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정할 도서 정보를 가져오기
                int selectedId = friend.getId();
                String selectedName = friend.getName();
                String selectedPhone = friend.getPhone();
                String selectedSchool = friend.getSchool();


                // 수정할 도서 정보를 사용하여 수정 대화상자 열기
                JFrame editFriendFrame = new JFrame("친구 정보 수정");
                editFriendFrame.setSize(400, 300);
                editFriendFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel editFriendPanel = new JPanel();
                editFriendFrame.getContentPane().add(editFriendPanel);
                editFriendPanel.setLayout(null);

                JLabel nameFriendLabel = new JLabel("이름:");
                nameFriendLabel.setBounds(30, 30, 80, 25);
                editFriendPanel.add(nameFriendLabel);

                JTextField nameTextField = new JTextField(selectedName);
                nameTextField.setBounds(100, 30, 250, 25);
                editFriendPanel.add(nameTextField);

                JLabel phoneLabel = new JLabel("전화번호:");
                phoneLabel.setBounds(30, 80, 80, 25);
                editFriendPanel.add(phoneLabel);

                JTextField phoneTextField = new JTextField(selectedPhone);
                phoneTextField.setBounds(100, 80, 250, 25);
                editFriendPanel.add(phoneTextField);
                
                JLabel schoolLabel = new JLabel("소속:");
                schoolLabel.setBounds(30, 130, 80, 25);
                editFriendPanel.add(schoolLabel);

                JTextField schoolTextField = new JTextField(selectedSchool);
                schoolTextField.setBounds(100, 130, 250, 25);
                editFriendPanel.add(schoolTextField);


                JButton saveFriendButton = new JButton("저장");
                saveFriendButton.setBounds(100, 180, 100, 25);
                editFriendPanel.add(saveFriendButton);

                saveFriendButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 수정된 도서 정보 가져오기
                        String updatedName = nameTextField.getText();
                        String updatedPhone = phoneTextField.getText();
                        String updatedSchool = schoolTextField.getText();

                        // 수정된 도서 정보를 적용하여 도서 정보 업데이트
                        for (Friend friend : friendList) {
                            if (friend.getId() == selectedId) {
                                friend.setName(updatedName);
                                friend.setPhone(updatedPhone);
                                friend.setSchool(updatedSchool);
                                // 파일에 수정된 정보 저장
                                updateFriend(friendList);
                                break;
                            }
                        }

                        // 수정 후 창 닫기
                        editFriendFrame.dispose();

                        // 수정된 정보로 도서 목록 다시 불러오기
                        updateFriendTable(friendList);
                        panel_7.setVisible(false);
                        panel_6.setVisible(true);
                    }
                });

                editFriendFrame.setVisible(true);
            }
        });


        panel_7.add(editFriendButton);
        
        // 도서 삭제 버튼 추가
        JButton deleteFriendButton = new JButton("친구 삭제");
        deleteFriendButton.setFont(new Font("카페24 심플해", Font.BOLD, 15));
        deleteFriendButton.setBounds(360, 300, 150, 30);
        deleteFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    // 도서 삭제 메소드 호출
                    deleteFriend(friend.getId()); // ISBN 번호를 인자로 전달하여 도서 삭제
                    JOptionPane.showMessageDialog(null, "친구가 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    // 삭제 후 패널을 닫거나 다른 처리를 수행할 수 있음
                    loadFriends();
                    panel_7.setVisible(false);
                    panel_6.setVisible(true);
                }
            }
        });

        panel_7.add(deleteFriendButton);
        
        // 테이블 생성 및 설정
        JTable table_2 = new JTable(model);
        JScrollPane scrollPane_2 = new JScrollPane(table_2);
        scrollPane_2.setBounds(20, 20, 480, 250);
        panel_7.add(scrollPane_2);
    }


    private void updateBook(ArrayList<Book> bookList) {
        String url = "jdbc:mysql://localhost:3305/book";
        String user = "root";
        String password = "";

        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, status = ?, majorStatus = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (Book book : bookList) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setString(3, book.getPublisher());
                pstmt.setString(4, book.getStatus());
                pstmt.setInt(5, book.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void updateFriend(ArrayList<Friend> friendList) {
        String url = "jdbc:mysql://localhost:3305/book";
        String user = "root";
        String password = "";

        String query = "UPDATE friends SET name = ?, phone = ?, school = ?, type = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (Friend friend : friendList) {
                pstmt.setString(1, friend.getName());
                pstmt.setString(2, friend.getPhone());
                pstmt.setString(3, friend.getSchool());
                pstmt.setString(4, friend.getType());
                pstmt.setInt(5, friend.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


 // 도서 삭제 메소드 (데이터베이스 버전)
    private void deleteBook(int id) {
        String url = "jdbc:mysql://localhost:3305/book";
        String user = "root";
        String password = "";

        String query = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // 도서 ID를 기반으로 해당 도서를 데이터베이스에서 삭제
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        loadBooks();
        loadLoans();
    }
    private void deleteFriend(int id) {
        String url = "jdbc:mysql://localhost:3305/book";
        String user = "root";
        String password = "";

        String query = "DELETE FROM friends WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // 도서 ID를 기반으로 해당 도서를 데이터베이스에서 삭제
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        loadFriends();
        loadLoans();
    }
}
