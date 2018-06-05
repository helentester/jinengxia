/**
 * @author Helen 
 * @date 2018年6月5日  
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

/**
 * 描述：excel事件处理
 */
public class MyExcel {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {
		MyExcel myExcel = new MyExcel();
		myExcel.readExcel();
	}
	
	/*读取excel文件中的数据，并生成数组*/
	@SuppressWarnings("deprecation")
	public Object[][] readExcel() throws IOException {
		BaseData bdata = new BaseData();
		
		File file = new File(bdata.getFilePath("src/test/java/testFile/TestData.xlsx"));//获取文件
		FileInputStream fileInputStream = new FileInputStream(file);//读数据
		
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("login");//读取指定标签页的数据
		int rowNum = sheet.getPhysicalNumberOfRows();//获取行数(获取的是物理行数，也就是不包括那些空行（隔行）的情况)
		int columNum = sheet.getRow(0).getPhysicalNumberOfCells();//获取列数
		
		Object[][]	data = new Object[rowNum][columNum];
		for(int i=1;i<rowNum;i++) {//从第二行开始取值
			for (int h = 0; h < columNum; h++) {
				sheet.getRow(i).getCell(h).setCellType(Cell.CELL_TYPE_STRING);//先把类型设置为string
				data[i][h] = sheet.getRow(i).getCell(h).getStringCellValue();//填充数组
				System.out.println(data[i][h]);
			}
		}
		workbook.close();
		return data;
	}

}
