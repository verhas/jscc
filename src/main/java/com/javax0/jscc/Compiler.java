package com.javax0.jscc;

import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * A very simple Java source compiler code based on the javac provided by the
 * JDK. This class can be used to compile sources that do not depend on any
 * other source code and thus can be compiled alone. The class compiled from the
 * code given in a String, generates the bytecode in memory and finally loads
 * the generated class, so that it can directly be instantiated and invoked
 * after the compiler returns.
 * 
 * @author Peter Verhas
 * 
 */
public class Compiler {

	private ClassLoader classLoader = Compiler.class.getClassLoader();

	private String compilerErrorOutput = null;

	/**
	 * Setting the parent class loader for the {@see ByteClassLoader} used to
	 * load the compiled byte code. This is needed when there are different
	 * class loaders in the application and the code compiling the class uses
	 * different class loader than the code wanting to use the compiled class.
	 * If no class loader is specified via this setter then the actual class
	 * loader will be used, which may work for most of the cases.
	 * 
	 * @param classLoader
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	private String calculateSimpleClassName(String canonicalClassName) {
		return canonicalClassName
				.substring(canonicalClassName.lastIndexOf('.') + 1);
	}

	/**
	 * Compile the Java code provided as a string and if the compilation was
	 * successful then load the class.
	 * 
	 * @param sourceCode
	 * @param canonicalClassName
	 *            the fully qualified name of the class
	 * @return the loaded class or null if the compilation was not successful
	 * @throws Exception
	 *             exceptions loading the class are not caught by the method
	 */
	public Class<?> compile(String sourceCode, String canonicalClassName)
			throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		List<JavaSourceFromString> sources = new LinkedList<>();
		String className = calculateSimpleClassName(canonicalClassName);
		sources.add(new JavaSourceFromString(className, sourceCode));

		StringWriter sw = new StringWriter();
		MemoryJavaFileManager fm = new MemoryJavaFileManager(
				compiler.getStandardFileManager(null, null, null));
		JavaCompiler.CompilationTask task = compiler.getTask(sw, fm, null,
				null, null, sources);

		Boolean compilationWasSuccessful = task.call();
		if (compilationWasSuccessful) {
			ByteClassLoader byteClassLoader = new ByteClassLoader(new URL[0],
					classLoader, classesByteArraysMap(fm));

			Class<?> klass = byteClassLoader.loadClass(canonicalClassName);
			byteClassLoader.close();
			return klass;
		} else {
			compilerErrorOutput = sw.toString();
			return null;
		}
	}

	private Map<String,byte[]> classesByteArraysMap(MemoryJavaFileManager fm){
		Map<String,byte[]>  map = new HashMap<String, byte[]>();
		for( String name : fm.getClassFileObjectsMap().keySet() ){
			MemoryFileObject mfo = fm.getClassFileObjectsMap().get(name);
			map.put(name, mfo.getByteArray());
		}
		return map;
	}
	
	public String getCompilerErrorOutput() {
		return compilerErrorOutput;
	}
}
