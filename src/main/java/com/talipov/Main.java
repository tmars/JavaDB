package com.talipov;

import java.sql.*;

public class Main {
    private static Connection conn;
    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException{
//        updateStudent(3, "Арни", new Date(1990,1,1), "m", 1);
//        selectAllStudent();

//        selectAllLections();
//        selectAllJournal();
        selectAllGroups();
    }

    private static void delete(String tableName, int id) {
        try {
            String sql = "DELETE FROM test_db."+tableName+ "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet select(String tableName, int id) {
        try {
            String sql = "SELECT * FROM test_db."+tableName+" WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

            return statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ResultSet selectByName(String tableName, String name) {
        try {
            String sql = "SELECT * FROM test_db."+tableName+" WHERE name=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();

            return statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ResultSet selectAll(String tableName) {
        try {
            String sql = "SELECT * FROM test_db."+tableName;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();

            return statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void insertStudent(String name, Date birthday, String sex, int groupId) {
        try {
            String sql = "INSERT INTO test_db.students(name, birthday, sex, group_id) " +
                    "VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, birthday);
            statement.setString(3, sex);
            statement.setInt(4, groupId);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(int id, String name, Date birthday, String sex, int groupId) {
        try {
            String sql = "UPDATE test_db.students SET name=?, birthday=?, sex=?, group_id=? " +
                    "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, birthday);
            statement.setString(3, sex);
            statement.setInt(4, groupId);
            statement.setInt(5, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int id) {
        delete("students", id);
    }

    private static void retStudent(ResultSet resultSet)
    {
        try {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("birthday"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("group_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectStudent(int id) {
        ResultSet resultSet = select("students", id);
        retStudent(resultSet);
    }

    public static void selectStudentByName(String name) {
        ResultSet resultSet = selectByName("students", name);
        retStudent(resultSet);
    }

    public static void selectAllStudent() {
        ResultSet resultSet = selectAll("students");

        try {
            while(resultSet.next()) {
                retStudent(resultSet);
                System.out.println("--");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // lections

    public static void insertLection(String name) {
        try {
            String sql = "INSERT INTO test_db.lections(name) " +
                    "VALUES(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLection(int id, String name) {
        try {
            String sql = "UPDATE test_db.lections SET name=? " +
                    "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLection(int id) {
        delete("lections", id);
    }

    private static void retLection(ResultSet resultSet)
    {
        try {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectLection(int id) {
        ResultSet resultSet = select("lections", id);
        retLection(resultSet);
    }

    public static void selectLectionByName(String name) {
        ResultSet resultSet = selectByName("lections", name);
        retLection(resultSet);
    }

    public static void selectAllLections() {
        ResultSet resultSet = selectAll("lections");

        try {
            while(resultSet.next()) {
                retLection(resultSet);
                System.out.println("--");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // group

    public static void insertGroup(String name) {
        try {
            String sql = "INSERT INTO test_db.group(name) " +
                    "VALUES(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateGroup(int id, String name) {
        try {
            String sql = "UPDATE test_db.group SET name=? " +
                    "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroup(int id) {
        delete("groups", id);
    }

    private static void retGroup(ResultSet resultSet)
    {
        try {
            System.out.println(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectGroup(int id) {
        ResultSet resultSet = select("groups", id);
        retGroup(resultSet);
    }

    public static void selectGroupByName(String name) {
        ResultSet resultSet = selectByName("groups", name);
        retGroup(resultSet);
    }

    public static void selectAllGroups() {
        ResultSet resultSet = selectAll("groups");

        try {
            while(resultSet.next()) {
                retGroup(resultSet);
                System.out.println("--");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // journal

    public static void insertJournal(int studentId, int lectionId) {
        try {
            String sql = "INSERT INTO test_db.group(student_id, group_id) " +
                    "VALUES(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, lectionId);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateJournal(int id, int studentId, int lectionId) {
        try {
            String sql = "UPDATE test_db.group SET student_id=?,lection_id=? " +
                    "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, lectionId);
            statement.setInt(3, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJournal(int id) {
        delete("journal", id);
    }

    public static void selectJournal(int id) {
        ResultSet resultSet = select("journal", id);
        try {
            System.out.println(resultSet.getString("group_id"));
            System.out.println(resultSet.getString("lection_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAllJournal() {
        ResultSet resultSet = selectAll("journal");

        try {
            while(resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("student_id"));
                System.out.println(resultSet.getString("lection_id"));
                System.out.println("--");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
