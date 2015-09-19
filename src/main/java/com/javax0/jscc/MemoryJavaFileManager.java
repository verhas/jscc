package com.javax0.jscc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryJavaFileManager extends
		ForwardingJavaFileManager<StandardJavaFileManager> {
	private static final Logger LOG = LoggerFactory
			.getLogger(MemoryJavaFileManager.class);
	private final Map<String, MemoryFileObject> classFilesMap;

	protected MemoryJavaFileManager(final StandardJavaFileManager fileManager) {
		super(fileManager);
		classFilesMap = new HashMap<>();
	}

	public Map<String, MemoryFileObject> getClassFileObjectsMap() {
		return classFilesMap;
	}

	@Override
	public JavaFileObject getJavaFileForOutput(final Location location,
			final String className, final Kind kind, final FileObject sibling)
			throws IOException {
		LOG.debug("getJavaFileForOutput({},{},{},{}", location, className,
				kind, sibling);
		MemoryFileObject classFile = new MemoryFileObject(className);
		classFilesMap.put(className, classFile);
		return classFile;
	}

}
