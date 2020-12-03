package teste.junit;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {

	@Test
	public void testData() {
		try {
			assertEquals("03122020", DateUtils.getDateAtualReportName());
			
			assertEquals("'2020-12-03'", DateUtils
					.formatDateSql(Calendar.getInstance().getTime()));
			
			assertEquals("2020-12-03", DateUtils
					.formatDateSqlSimple(Calendar.getInstance().getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		

	}

}
