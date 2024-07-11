package com.multi.ex04.filecopy;

import java.io.*;

public class FileCopyUseBuffer {

	// 1. 1byte씩 복사하는 고전적인 방법 = 가장 느린 방법!
	public static int fileCopyStreamVer(File source, File dest) {
		int size = 0; // 파일 사이즈
		// Try-catch resource : close를 하지 않아도 된다. Closeable를 상속받은 객체만 활용가능!!
		try (FileInputStream fis = new FileInputStream(source);
			 FileOutputStream fos = new FileOutputStream(dest);) {
			int read = 0;
			while ((read = fis.read()) != -1) {
				fos.write(read);
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // 에러일 경우 -1, 에러 설계!
		}
		return size;
	}

	// 2. Bufferd를 사용하는 복사 버전 - 고전 문법 버전
	//   -> 성능이 비약적으로 향상!
	//   -> 이 버전은 쓰지 말것 권장!
	public static int fileCopyBufferdStreamVer(File source, File dest) {
		int size = 0;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			// 오픈부
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dest);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);

			int read = 0;
			while((read = bis.read()) != -1) {
				bos.write(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			// close부 작성 요령 -> 열린순서의 역순으로 닫는다.
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return size;
	}

	// 3. Bufferd를 사용하는 복사 버전 - try-catch-resource문 활용 ★★★★★★
	//   -> 성능이 비약적으로 향상!
	//   -> 문법이 짧아져서 활용 할 만하다.
	public static int fileCopyBufferdStreamVer2(File source, File dest) {
		int size = 0;
		try	(FileInputStream fis = new FileInputStream(source);
				FileOutputStream fos = new FileOutputStream(dest);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {

			int read = 0;
			while((read = bis.read()) != -1) {
				bos.write(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return size;
	}


	public static int fileCopyReadWriteVer(File source, File dest) {
		int size = 0; // 문자열의 갯수

		try(FileReader fr = new FileReader(source);
			FileWriter fw = new FileWriter(dest);
		) {
			int read = 0;
			while((read = fr.read()) != -1) {
				fw.write(read);
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return size;
	}

	// BufferdReadWrite 문자열을 다룰때 가장 편한 방법!!!
	// BufferedReader -> readLine() 제일 편한 Text파일 다루는 메소드! ★★★★★
	//	★ 필드에서 제일 많이 사용하는 형태
	public static int fileCopyBufferdReadWriteVer(File source, File dest) {
		int readLineCount = 0;
		try(FileReader fr = new FileReader(source);
			FileWriter fw = new FileWriter(dest);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
		) {
			String readLine = null;
			while((readLine = br.readLine()) != null ) {
				bw.append(readLine);
				bw.newLine();
				readLineCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return readLineCount;
	}


	public static void main(String[] args) {
		System.out.println("시작");
		File source = new File("./13_IO/covid.txt");

		long start = System.currentTimeMillis();
		int count = 0;
		while(count++ < 100) {
			FileCopy.fileCopyStreamVer(source, new File("13_IO/c_covid"+count+".txt"));
		}
		long end = System.currentTimeMillis();

		long estimated = end - start;
		System.out.println("fileCopyStreamVer estimated : " + estimated);

		start = System.currentTimeMillis();
		count = 0;
		while(count++ < 100) {
			FileCopyUseBuffer.fileCopyBufferdStreamVer(source, new File("13_IO/c2_covid"+count+".txt"));
		}
		end = System.currentTimeMillis();

		estimated = end - start;
		System.out.println("fileCopyBufferdStreamVer estimated : " + estimated);

//		fileCopyStreamVer estimated : 5778
//		fileCopyBufferdStreamVer estimated : 90


//		fileCopyStreamVer estimated : 7750
//		fileCopyBufferdStreamVer estimated : 77

//		fileCopyStreamVer estimated : 13996
//		fileCopyBufferdStreamVer estimated : 150

//		fileCopyStreamVer estimated : 14768
//		fileCopyBufferdStreamVer estimated : 180
//
//		fileCopyStreamVer estimated : 17231
//		fileCopyBufferdStreamVer estimated : 124
//
//		fileCopyStreamVer estimated : 9058
//		fileCopyBufferedStreamVer estimated : 137
	}


}