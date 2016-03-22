package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class AdminBill extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	
	private String strValue="";
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	
	
	
	void showmenuTable() //显示菜谱表中的用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM bill";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("t"),
						rs.getString("id"),rs.getString("sell"), rs.getString("cost"), rs.getString("profit"), (rs.getString("status").equals("1")?"已结账":"未结账") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	public AdminBill() {
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("\u7ED3\u8D26\u7BA1\u7406");
		setBounds(100, 100, 838, 376);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "日期", "餐桌号","总价格", "总成本","利润","状态"};// 定义表格列名数组
		String[][] tableValues = {};// 定义表格数据数组
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton returnButton = new JButton("\u8FD4\u56DE");
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Administrator Frame=new Administrator();
				Frame.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(returnButton);
		
		showmenuTable();
		
	}
}