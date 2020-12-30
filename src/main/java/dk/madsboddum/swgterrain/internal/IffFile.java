package dk.madsboddum.swgterrain.internal;

import org.apache.mina.core.buffer.IoBuffer;

import java.io.File;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class IffFile {
	
	/**
	 * 
	 * Reads in a client file, and uses the given interpreter to interpret the data.
	 * See {@link VisitorInterface}
	 * 
	 * @param file the file to read
	 * @param i the interpreter to use
	 */
		
	public static void readFile(File input, VisitorInterface i) {
		
		IoBuffer buffer;
		try {
			input.setReadable(true);
			input.setWritable(true);
			
			java.io.FileInputStream fis = new java.io.FileInputStream(input);
			
			buffer = IoBuffer.allocate(fis.available(), false);
			buffer.setAutoExpand(true);
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			
			byte[] buf = new byte[1024];
			int count = fis.read(buf);
			while(count != -1) {
				buffer.put(buf, 0, count);
				count = fis.read(buf);
			}
			
			buffer.flip();
		
			parseIffStructure(buffer, i, 0);
			
			fis.close();
			
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	private static int getNameLength(byte[] data) {
		int count = 0;
		for(byte b : data) {
			if(!((b >= 'A' && b <= 'Z') || (b >= '0' && b <= '9') || b == ' ')) {
				break;
			} else {
				count++;
			}
		}
		
		if(count < 4) {
			return 0;
		} else if(count < 8) {
			return 4;
		} else {
			return 8;
		}
	}
	
	private static boolean childIsData(IoBuffer buf) {
		byte[] nameBuf = new byte[8];
		
		int length;
		if(buf.remaining() >= 8) {
			buf.mark();
			buf.get(nameBuf);
			buf.reset();
			
			length = getNameLength(nameBuf);
		} else {
			length = 0;
		}
		
		return length == 0;
	}
	
	private static boolean isFolderNode(String name) {
		
		if(name.startsWith("FORM") || name.endsWith("FORM")) 
			return true;
		
		return false;
		
	}
	
	private static void parseIffStructure(IoBuffer buf, VisitorInterface i, int depth) throws Exception {
		byte[] nameBuf = new byte[8];
		//Get the length of the Name
		
		int length;
		if(buf.remaining() >= 8) {
			buf.mark();
			buf.get(nameBuf);
			buf.reset();
			
			length = getNameLength(nameBuf);
		} else {
			length = 0;
		}
		
				
		String name = buf.getString(length, StandardCharsets.US_ASCII.newDecoder());
								
		int size = Integer.reverseBytes(buf.getInt());
		
		if(size > buf.remaining()) {
			if(name.length() > 0)
			return;
		}
		
		
		if(size < 0)
			return;
		
		if(!isFolderNode(name)) {
			IoBuffer dataBuf = buf.getSlice(size);
			dataBuf.order(ByteOrder.LITTLE_ENDIAN);
			i.parseData(name, dataBuf, depth, size);
		} else {
			i.notifyFolder(name, depth);
			
			IoBuffer folderBuffer = buf.getSlice(size);
			folderBuffer.order(ByteOrder.LITTLE_ENDIAN);
			while(folderBuffer.remaining() > 0) {
				parseIffStructure(folderBuffer, i, depth+1);
			}
		}

	}
	
}
