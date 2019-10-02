package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtils 
{
	Workbook wb;
	public ExcelFileUtils()throws Throwable
	{
		
		FileInputStream fis=new FileInputStream("D:\\@KK@\\VasuStockAccounting\\TestInput\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	//row count
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//column count
	public int colCount(String sheetname, int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
	//reading the data from excel file
	public String getData(String sheetname,int row,int column)
	{
	
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int cellData=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(cellData);
		}else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
	}
	public void setData(String sheetname,int row,int column,String status)throws Throwable
	{
	//Writing data into excel Pass and Fail and NotExecuted
	Sheet sh=wb.getSheet(sheetname);
	Row rownum=sh.getRow(row);
	Cell cell=rownum.createCell(column);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("PASS"))
	{
		//create cell style
		CellStyle style=wb.createCellStyle();
		//create font
		Font font=wb.createFont();
		//apply color to the text
		font.setColor(IndexedColors.GREEN.index);
		//apply bold to the text
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);
		}else
			if(status.equalsIgnoreCase("FAIL"))
			{
				//create cell style
				CellStyle style=wb.createCellStyle();
				//create font
				Font font=wb.createFont();
				//apply color to the text
				font.setColor(IndexedColors.RED.index);
				//apply bold to the text
				font.setBold(true);
				//set font
				style.setFont(font);
				//set cell style
				rownum.getCell(column).setCellStyle(style);
			}else
				if(status.equalsIgnoreCase("NOT EXECUTED"))
				{
					//create cell style
					CellStyle style=wb.createCellStyle();
					//create font
					Font font=wb.createFont();
					//apply color to the text
					font.setColor(IndexedColors.BLUE.index);
					//apply bold to the text
					font.setBold(true);
					//set font
					style.setFont(font);
					//set cell style
					rownum.getCell(column).setCellStyle(style);
				}
	FileOutputStream fos=new FileOutputStream("D:\\@KK@\\VasuStockAccounting\\TestOutput\\OutPutSheet.xlsx");
	wb.write(fos);
	fos.close();
	}
	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtils excel=new ExcelFileUtils();
		System.out.println(excel.rowCount("MasterTestCases"));
	
	}

}
