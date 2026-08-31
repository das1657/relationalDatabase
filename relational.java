import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;



public class relational {
    static String databaseString = System.getProperty("user.dir");

    static int perm = 0;
    public static void main(String[] args) throws SQLException {
        chooseAccount();
        

    }



    public static void insertPerson(String person, String job, String department) throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");
        //Connection c = DriverManager.getConnection("jdbc:ucanaccess://G:\\My Drive\\Database\\java_sweeng.accdb");

        Statement s = c.createStatement(); 
        s.executeUpdate("insert into people(person, job, department)values('"+person+"','"+job+"','"+department+"')");
        String sql = "SELECT * FROM people";
             
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
         
        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("person");
            String job2 = result.getString("job");
            String dep2 = result.getString("department");
            
            
             
            System.out.println(id + ", " + name + ", " + job2 + ", " + dep2);

        }

    }

    
    public static void chooseAccount(){
        JFrame f = new JFrame("Choose Account");

        JButton b1 = new JButton("Employee");
        b1.setBounds(90, 100, 180, 40);
        f.add(b1);
        
         b1.addActionListener(new ActionListener(){

            //@Override
            public void actionPerformed(ActionEvent arg0) {
                f.setVisible(false);

                try {
                    employeePage();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

         });
        
        JButton b2 = new JButton("Manager");
        b2.setBounds(90, 150, 180, 40);
        f.add(b2);

        b2.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                f.setVisible(false);
                try {
                    signInPage();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

         });
         /* 
         
        JButton b3 = new JButton("Initial Load");
        b3.setBounds(90, 200, 180, 40);
        f.add(b3);

        b3.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    insertPerson("unnamed", "a job", "a department" );
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

         });

        */

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
    


    public static void startPage(){
        JFrame f = new JFrame("Start");

        JButton bak = new JButton("back");
        bak.setBounds(0, 0, 80, 40);
        f.add(bak);
        
         bak.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                perm = 0;
                f.setVisible(false);
                chooseAccount();
            }

         });


        JButton b1 = new JButton("Register");
        b1.setBounds(90, 50, 180, 40);
        if (perm > 1){
            f.add(b1);
        }
        
        
         b1.addActionListener(new ActionListener(){

            //@Override
            public void actionPerformed(ActionEvent arg0) {
                f.setVisible(false);

                registerPage();
            }

         });
        
        JButton b2 = new JButton("Sign into Manager");
        b2.setBounds(90, 100, 180, 40);
        f.add(b2);

        b2.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                f.setVisible(false);
                try {
                    managerPage();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

         });

         
        
         
         /* 
        JButton b3 = new JButton("Change Password");
        b3.setBounds(90, 150, 180, 40);
        f.add(b3);

        b3.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    changePassword();
                    f.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

         });
*/
        JButton b4 = new JButton("Add Job");
        b4.setBounds(90, 200, 180, 40);

        if (perm > 1){
            f.add(b4);
        }

        //f.add(b4);

        b4.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addJob();
                    f.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

         });

        JButton b5 = new JButton("View Jobs");
        b5.setBounds(90, 250, 180, 40);
        f.add(b5);

        b5.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    viewJobs();
                    f.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

         });

        JButton b6 = new JButton("Add Department");
        b6.setBounds(90, 300, 180, 40);
        f.add(b6);

        b6.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addDepartment();
                    f.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

         });

        JButton b7 = new JButton("View Departments");
        b7.setBounds(90, 350, 180, 40);
        f.add(b7);

        b7.addActionListener(new ActionListener(){
            
            //@Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    viewDepartments();
                    f.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

         });

        

        f.setSize(400, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public static void signInPage() throws SQLException{
        JFrame frame = new JFrame("sign in");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\users.accdb");

        Statement s = c.createStatement(); 
        Statement statement = c.createStatement();
        String sql = "SELECT * FROM users";
        ResultSet result = statement.executeQuery(sql);


        
        
        JTextField name = new JTextField();
        name.setBounds(90, 50, 180, 30); 

        JLabel nameLabel = new JLabel("Password");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                chooseAccount();
            }

         });

         JButton b2 = new JButton("Enter");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {
                    
                    while (result.next()){
                        if (name.getText().equals(result.getString("Password"))){
                            perm = result.getInt("Permission");
                            frame.setVisible(false);
                            startPage();
                            break;
                        } else {
                            
                        }
                    }
/* 
                    String extractedPassword = "";
                    
                    File file = new File("Z:\\relationalDatabase\\password.txt");
                    Scanner sc = new Scanner(file);
                    
                    while (sc.hasNextLine()){
                        extractedPassword = sc.nextLine();
                    }

                    if (extractedPassword.equals(name.getText())){
                        frame.setVisible(false);
                        startPage();
                    } else {
                        System.out.println("WRONG!!!");
                        JOptionPane.showMessageDialog(frame,"The password is incorrect","Error", JOptionPane.ERROR_MESSAGE);

                    }
                        */


                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
 
          });


        




        panel.add(name);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void registerPage(){

        JFrame frame = new JFrame("register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        
        
        
        JTextField name = new JTextField();
        name.setBounds(90, 50, 180, 30); 

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        
        JTextField job = new JTextField();
        job.setBounds(90, 110, 180, 30); 

        JLabel jobLabel = new JLabel("Job Title");
        jobLabel.setBounds(90, 70, 180, 40);
        frame.add(jobLabel);
        
        JTextField department = new JTextField();
        department.setBounds(90, 170, 180, 30); 

        JLabel departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(90, 130, 180, 40);
        frame.add(departmentLabel);
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                startPage();
            }

         });

         JButton b2 = new JButton("Add");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {

                    if (doesItExist("department", department.getText()) && doesItExist("job", job.getText())){
                        insertPerson(name.getText(), job.getText(), department.getText());
                        JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);   
                    } else {
                        JOptionPane.showMessageDialog(frame,"One of the entries is invalid, please try again", "Error", JOptionPane.ERROR_MESSAGE);    
                    }


                    //insertPerson(name.getText(), job.getText(), department.getText());
                    //JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
             }
 
          });


        




        panel.add(name);
        panel.add(department);
        panel.add(job);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public static void addJob(){

        JFrame frame = new JFrame("add job");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        
        
        
        JTextField name = new JTextField();
        name.setBounds(90, 50, 180, 30); 

        JLabel nameLabel = new JLabel("Letter");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        
        JTextField job = new JTextField();
        job.setBounds(90, 110, 180, 30); 

        JLabel jobLabel = new JLabel("Job Title");
        jobLabel.setBounds(90, 70, 180, 40);
        frame.add(jobLabel);
        
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                startPage();
            }

         });

         JButton b2 = new JButton("Add");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {

                    if (doesItExist("job",name.getText()) == false && doesItExist("job",job.getText()) == false){
                        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");
                        //Connection c = DriverManager.getConnection("jdbc:ucanaccess://G:\\My Drive\\Database\\java_sweeng.accdb");

                        Statement s = c.createStatement(); 
                        s.executeUpdate("insert into job(letter, title)values('"+name.getText()+"','"+job.getText()+"')");
                        JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);   
                    } else {
                        JOptionPane.showMessageDialog(frame,"One of the entries is invalid, please try again", "Error", JOptionPane.ERROR_MESSAGE);    
                    }


                    //insertPerson(name.getText(), job.getText(), department.getText());
                    //JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
             }
 
          });


        




        panel.add(name);
        panel.add(job);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void editPage(String gotname, String gotjob, String gotdepartment, int gotid, boolean wasSearched, String query) throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 


        JFrame frame = new JFrame("edit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        
        
        
        JTextField name = new JTextField(gotname);
        name.setBounds(90, 50, 180, 30); 

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        
        JTextField job = new JTextField(gotjob);
        job.setBounds(90, 110, 180, 30); 

        JLabel jobLabel = new JLabel("Job Title");
        jobLabel.setBounds(90, 70, 180, 40);
        frame.add(jobLabel);
        
        JTextField department = new JTextField(gotdepartment);
        department.setBounds(90, 170, 180, 30); 

        JLabel departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(90, 130, 180, 40);
        frame.add(departmentLabel);
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                try {
                    if (wasSearched){
                        managerPageSearch(query);

                    } else {
                        managerPage();
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

         });

         JButton b2 = new JButton("Edit");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {
                    if (doesItExist("department", department.getText()) && doesItExist("job", job.getText())){
                        s.executeUpdate("update people set person='"+name.getText()+"',job='"+job.getText()+"',department='"+department.getText()+"'where ID='"+gotid+"'");
                    JOptionPane.showMessageDialog(frame,"Successfully edited", "Edit", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        System.out.println(doesItExist("department", department.getText()));
                        System.out.println(doesItExist("job", job.getText()));
                        JOptionPane.showMessageDialog(frame,"One of the entries is invalid, please try again", "Error", JOptionPane.ERROR_MESSAGE);

                    }



                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
             }
 
          });

        panel.add(name);
        panel.add(department);
        panel.add(job);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void managerPage() throws SQLException{

        JFrame frame = new JFrame("frame");
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        JPanel contentPanel = new JPanel();
        Statement statement = c.createStatement();
        String sql = "SELECT * FROM people";
        String JOBsql = "SELECT * FROM job";
        String DEPsql = "SELECT * FROM department";
        ResultSet result = statement.executeQuery(sql);



        int numberOfEntries = 0;
        while (result.next()) {
            numberOfEntries += 1;
            int id = result.getInt("ID");
            String name = result.getString("person");
            String job = result.getString("job");
            String dep = result.getString("department");

        ResultSet JOBresult = statement.executeQuery(JOBsql);
        ResultSet DEPresult = statement.executeQuery(DEPsql);

            String jobString = "N/A";
            String depString = "N/A";

            while (JOBresult.next()) {
                if (job.equals(JOBresult.getString("letter"))){
                    jobString = JOBresult.getString("title");
                }

            }

            while (DEPresult.next()) {
                if (dep.equals(DEPresult.getString("letter"))){
                    depString = DEPresult.getString("department");
                }

            }
            
            JLabel nameLabel = new JLabel("Name: " + name);
            nameLabel.setBounds(10, 15+(numberOfEntries*80), 400, 40);
            contentPanel.add(nameLabel);

            JLabel jobLabel = new JLabel("Job: " + jobString);
            jobLabel.setBounds(10, 30+(numberOfEntries*80), 400, 40);
            contentPanel.add(jobLabel);

            JLabel departmentLabel = new JLabel("Department: " + depString);
            departmentLabel.setBounds(10, 45+(numberOfEntries*80), 400, 40);
            contentPanel.add(departmentLabel);

            JButton del = new JButton("delete");
            del.setBounds(650, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(del);
             del.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("deleted");
                    contentPanel.setVisible(false);
                    try {
                        s.executeUpdate("delete from people where ID='"+id+"'");
                        frame.setVisible(false);
                        managerPage();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });

            JButton edit = new JButton("edit");
            edit.setBounds(500, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(edit);
             edit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    contentPanel.setVisible(false);
                    try {
                        editPage(name, job, dep, id, false, "");
                        frame.setVisible(false);
                        
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });
            //System.out.println(name + ", " + adr + ", " + ag + ", " + sal);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel.setLayout(null); 
        contentPanel.setPreferredSize(new Dimension(800, 400 + (70*numberOfEntries))); 
        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 20);
        contentPanel.add(b1);
         b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                startPage();
            }
         });

        JTextField search = new JTextField("Search");
        search.setBounds(90, 20, 300, 30); 
        contentPanel.add(search);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(400, 20, 80, 30);
        contentPanel.add(searchButton);
         searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("searching");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                try {
                    managerPageSearch(search.getText());
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         });

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void employeePage() throws SQLException {

        JFrame frame = new JFrame("frame");
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        JPanel contentPanel = new JPanel();
        Statement statement = c.createStatement();
        String sql = "SELECT * FROM people";
        String JOBsql = "SELECT * FROM job";
        String DEPsql = "SELECT * FROM department";
        ResultSet result = statement.executeQuery(sql);

        int numberOfEntries = 0;
        while (result.next()) {
            numberOfEntries += 1;
            int id = result.getInt("ID");
            String name = result.getString("person");
            String job = result.getString("job");
            String dep = result.getString("department");

        ResultSet JOBresult = statement.executeQuery(JOBsql);
        ResultSet DEPresult = statement.executeQuery(DEPsql);

            String jobString = "N/A";
            String depString = "N/A";

            while (JOBresult.next()) {
                if (job.equals(JOBresult.getString("letter"))){
                    jobString = JOBresult.getString("title");
                }

            }

            while (DEPresult.next()) {
                if (dep.equals(DEPresult.getString("letter"))){
                    depString = DEPresult.getString("department");
                }

            }
            
            JLabel nameLabel = new JLabel("Name: " + name);
            nameLabel.setBounds(10, 15+(numberOfEntries*80), 400, 40);
            contentPanel.add(nameLabel);

            JLabel jobLabel = new JLabel("Job: " + jobString);
            jobLabel.setBounds(10, 30+(numberOfEntries*80), 400, 40);
            contentPanel.add(jobLabel);

            JLabel departmentLabel = new JLabel("Department: " + depString);
            departmentLabel.setBounds(10, 45+(numberOfEntries*80), 400, 40);
            contentPanel.add(departmentLabel);
            
            //System.out.println(name + ", " + adr + ", " + ag + ", " + sal);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel.setLayout(null); 
        contentPanel.setPreferredSize(new Dimension(800, 400 + (70*numberOfEntries))); 
        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 20);
        contentPanel.add(b1);
         b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                chooseAccount();
            }
         });
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // this function was used in a previous assignment, however on this current one it is unused. i understand having a text file to contain a password that it reads is Really Bad but this was for an assignment
    public static void changePassword(){
        JFrame frame = new JFrame("change password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        
        
        
        JTextField name = new JTextField();
        name.setBounds(90, 50, 180, 30); 
        frame.add(name);

        JLabel nameLabel = new JLabel("New Password");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        
        JTextField confirm = new JTextField();
        confirm.setBounds(90, 120, 180, 30); 
        frame.add(confirm);

        JLabel confirmLabel = new JLabel("Enter Old Password");
        confirmLabel.setBounds(90, 80, 180, 40);
        frame.add(confirmLabel);
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                startPage();
            }

         });

         JButton b2 = new JButton("Change");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {
                    String extractedPassword = "";
                    
                    File file = new File("Z:\\relationalDatabase\\password.txt");
                    Scanner sc = new Scanner(file);
                    
                    while (sc.hasNextLine()){
                        extractedPassword = sc.nextLine();
                    }

                    if (extractedPassword.equals(confirm.getText())){

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                            writer.write(name.getText());
                        } catch (IOException e) {
                            e.printStackTrace(); // Handle potential I/O errors
                        }
                        frame.setVisible(false);
                        startPage();
                    } else {
                        JOptionPane.showMessageDialog(frame,"Old password does not match","Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("WRONG!!!");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
 
          });


        




        panel.add(name);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static boolean doesItExist(String type, String thing) throws SQLException{
            
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        Statement statement = c.createStatement();
        String sql = "SELECT * FROM people";
        String JOBsql = "SELECT * FROM job";
        String DEPsql = "SELECT * FROM department";
        ResultSet result = statement.executeQuery(sql);


        if (type.equals("job")){
            ResultSet JOBresult = statement.executeQuery(JOBsql);

            while (JOBresult.next()) {
                if (thing.equals(JOBresult.getString("letter"))){
                    return true;
                    
                }
            
                if (thing.equals(JOBresult.getString("title"))){
                    return true;
                }
                
            }
            return false;

        } else if (type.equals("department")){
            ResultSet DEPresult = statement.executeQuery(DEPsql);

            while (DEPresult.next()) {

                if (thing.equals(DEPresult.getString("letter"))){
                    return true;
                }
            
                if (thing.equals(DEPresult.getString("department"))){
                    return true;
                }
                

            }
            return false;
        }
        
        return true;
    }

    public static void viewJobs() throws SQLException{

        JFrame frame = new JFrame("frame");
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        JPanel contentPanel = new JPanel();
        Statement statement = c.createStatement();
        String JOBsql = "SELECT * FROM job";
        ResultSet result = statement.executeQuery(JOBsql);

        int numberOfEntries = 0;
        while (result.next()) {
            numberOfEntries += 1;
            int id = result.getInt("ID");
            String letter = result.getString("letter");
            String title = result.getString("title");

            
            JLabel nameLabel = new JLabel("Letter: " + letter);
            nameLabel.setBounds(10, 15+(numberOfEntries*80), 400, 40);
            contentPanel.add(nameLabel);

            JLabel jobLabel = new JLabel("Job: " + title);
            jobLabel.setBounds(10, 30+(numberOfEntries*80), 400, 40);
            contentPanel.add(jobLabel);

            JButton del = new JButton("delete");
            del.setBounds(650, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(del);
             del.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("deleted");
                    contentPanel.setVisible(false);
                    try {
                        s.executeUpdate("delete from job where ID='"+id+"'");
                        frame.setVisible(false);
                        viewJobs();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });


            //System.out.println(name + ", " + adr + ", " + ag + ", " + sal);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel.setLayout(null); 
        contentPanel.setPreferredSize(new Dimension(800, 400 + (70*numberOfEntries))); 
        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 20);
        contentPanel.add(b1);
         b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                startPage();
            }
         });
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void addDepartment(){

        JFrame frame = new JFrame("add department");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 300);
        
        
        
        
        JTextField name = new JTextField();
        name.setBounds(90, 50, 180, 30); 

        JLabel nameLabel = new JLabel("Letter");
        nameLabel.setBounds(90, 10, 180, 40);
        frame.add(nameLabel);
        
        JTextField job = new JTextField();
        job.setBounds(90, 110, 180, 30); 

        JLabel jobLabel = new JLabel("Department");
        jobLabel.setBounds(90, 70, 180, 40);
        frame.add(jobLabel);
        
        

        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 40);
        frame.add(b1);
        
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                frame.setVisible(false);
                startPage();
            }

         });

         JButton b2 = new JButton("Add");
         b2.setBounds(90, 280, 180, 40);
         frame.add(b2);

          b2.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 try {

                    if (doesItExist("department",name.getText()) == false && doesItExist("department",job.getText()) == false){
                        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");
                        //Connection c = DriverManager.getConnection("jdbc:ucanaccess://G:\\My Drive\\Database\\java_sweeng.accdb");

                        Statement s = c.createStatement(); 
                        s.executeUpdate("insert into department(letter, department)values('"+name.getText()+"','"+job.getText()+"')");
                        JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);   
                    } else {
                        JOptionPane.showMessageDialog(frame,"One of the entries is invalid, please try again", "Error", JOptionPane.ERROR_MESSAGE);    
                    }


                    //insertPerson(name.getText(), job.getText(), department.getText());
                    //JOptionPane.showMessageDialog(frame,"Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
             }
 
          });


        




        panel.add(name);
        panel.add(job);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void viewDepartments() throws SQLException{

        JFrame frame = new JFrame("frame");
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        JPanel contentPanel = new JPanel();
        Statement statement = c.createStatement();
        String JOBsql = "SELECT * FROM department";
        ResultSet result = statement.executeQuery(JOBsql);

        int numberOfEntries = 0;
        while (result.next()) {
            numberOfEntries += 1;
            int id = result.getInt("ID");
            String letter = result.getString("letter");
            String title = result.getString("department");

            
            JLabel nameLabel = new JLabel("Letter: " + letter);
            nameLabel.setBounds(10, 15+(numberOfEntries*80), 400, 40);
            contentPanel.add(nameLabel);

            JLabel jobLabel = new JLabel("Department: " + title);
            jobLabel.setBounds(10, 30+(numberOfEntries*80), 400, 40);
            contentPanel.add(jobLabel);

            JButton del = new JButton("delete");
            del.setBounds(650, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(del);
             del.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("deleted");
                    contentPanel.setVisible(false);
                    try {
                        s.executeUpdate("delete from department where ID='"+id+"'");
                        frame.setVisible(false);
                        viewDepartments();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });


            //System.out.println(name + ", " + adr + ", " + ag + ", " + sal);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel.setLayout(null); 
        contentPanel.setPreferredSize(new Dimension(800, 400 + (70*numberOfEntries))); 
        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 20);
        contentPanel.add(b1);
         b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                startPage();
            }
         });
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void managerPageSearch(String query) throws SQLException{

        JFrame frame = new JFrame("Searched for: " + query);
        Connection c = DriverManager.getConnection("jdbc:ucanaccess://" + databaseString + "\\lib\\manager.accdb");

        Statement s = c.createStatement(); 
        JPanel contentPanel = new JPanel();
        Statement statement = c.createStatement();
        String sql = "SELECT * FROM people";
        String JOBsql = "SELECT * FROM job";
        String DEPsql = "SELECT * FROM department";
        ResultSet result = statement.executeQuery(sql);

        int numberOfEntries = 0;
        while (result.next()) {
            
            int id = result.getInt("ID");
            String name = result.getString("person");
            String job = result.getString("job");
            String dep = result.getString("department");

                    ResultSet JOBresult = statement.executeQuery(JOBsql);
        ResultSet DEPresult = statement.executeQuery(DEPsql);

            String jobString = "N/A";
            String depString = "N/A";

            while (JOBresult.next()) {
                if (job.equals(JOBresult.getString("letter"))){
                    jobString = JOBresult.getString("title");
                }

            }

            while (DEPresult.next()) {
                if (dep.equals(DEPresult.getString("letter"))){
                    depString = DEPresult.getString("department");
                }

            }

            if ((name.toLowerCase().equals(query.toLowerCase())) || (jobString.toLowerCase().equals(query.toLowerCase())) || (depString.toLowerCase().equals(query.toLowerCase()))){
                numberOfEntries += 1;


            
            JLabel nameLabel = new JLabel("Name: " + name);
            nameLabel.setBounds(10, 15+(numberOfEntries*80), 400, 40);
            contentPanel.add(nameLabel);

            JLabel jobLabel = new JLabel("Job: " + jobString);
            jobLabel.setBounds(10, 30+(numberOfEntries*80), 400, 40);
            contentPanel.add(jobLabel);

            JLabel departmentLabel = new JLabel("Department: " + depString);
            departmentLabel.setBounds(10, 45+(numberOfEntries*80), 400, 40);
            contentPanel.add(departmentLabel);

            JButton del = new JButton("delete");
            del.setBounds(650, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(del);
             del.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("deleted");
                    contentPanel.setVisible(false);
                    try {
                        s.executeUpdate("delete from people where ID='"+id+"'");
                        frame.setVisible(false);
                        managerPageSearch(query);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });

            JButton edit = new JButton("edit");
            edit.setBounds(500, 15+(numberOfEntries*80), 80, 45);
            contentPanel.add(edit);
             edit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    contentPanel.setVisible(false);
                    try {
                        editPage(name, job, dep, id, true, query);
                        frame.setVisible(false);
                        
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
             });

            }

        
            //System.out.println(name + ", " + adr + ", " + ag + ", " + sal);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel.setLayout(null); 
        contentPanel.setPreferredSize(new Dimension(800, 400 + (70*numberOfEntries))); 
        JButton b1 = new JButton("back");
        b1.setBounds(0, 0, 80, 20);
        contentPanel.add(b1);
         b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("back");
                contentPanel.setVisible(false);
                frame.setVisible(false);
                try {
                    managerPage();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         });
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

