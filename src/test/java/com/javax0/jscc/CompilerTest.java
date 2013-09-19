package com.javax0.jscc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class CompilerTest {

	private String loadJavaSource(String name) throws IOException {
		InputStream is = this.getClass().getResourceAsStream(name);
		byte[] buf = new byte[3000];
		int len = is.read(buf);
		is.close();
		return new String(buf, 0, len, "utf-8");
	}

	@Test
	public void given_PerfectSourceCode_when_CallingCompiler_then_ProperClassIsReturned()
			throws Exception {
		final String source = loadJavaSource("Test1.java");
		Compiler compiler = new Compiler();
		Class<?> newClass = compiler.compile(source, "com.javax0.jscc.Test1");
		Object object = newClass.newInstance();
		Method f = newClass.getMethod("a");
		String s = (String) f.invoke(object, null);
		Assert.assertEquals("x", s);
	}

	@Test
	public void given_ErroneousSourceCode_when_CallingCompiler_then_ReturnsNullClass()
			throws Exception {
		final String source = loadJavaSource("Test2.java");
		Compiler compiler = new Compiler();
		compiler.setClassLoader(this.getClass().getClassLoader());
		Class<?> newClass = compiler.compile(source, "com.javax0.jscc.Test2");
		Assert.assertNull(newClass);
		String s = compiler.getCompilerErrorOutput();
		Assert.assertNotNull(s);
	}
}
