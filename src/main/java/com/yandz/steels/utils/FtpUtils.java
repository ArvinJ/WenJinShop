package com.yandz.steels.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class FtpUtils {
	public static Logger logger = (Logger) LoggerFactory.getLogger(FtpUtils.class);
	// ftp服务器地址 ftp://127.0.0.1/
	public String hostname = "127.0.0.1";
	// ftp服务器端口号默认为21
	public Integer port = 21;
	// ftp登录账号
	public String username = "ftper";
	// ftp登录密码
	public String password = "123456";

	public FTPClient ftpClient = null;

	/**
	 * 初始化ftp服务器
	 */
	public void initFtpClient() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
		try {
			logger.info("connecting...ftp服务器:" + this.hostname + ":" + this.port);
			ftpClient.connect(hostname, port); // 连接ftp服务器
			ftpClient.login(username, password); // 登录ftp服务器
			int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.info("connect failed...ftp服务器:" + this.hostname + ":" + this.port);
			}
			logger.info("connect successfu...ftp服务器:" + this.hostname + ":" + this.port);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param pathname
	 *            ftp服务保存地址
	 * @param fileName
	 *            上传到ftp的文件名
	 * @param originfilename
	 *            待上传文件的名称（绝对地址） *
	 * @return
	 */
	public boolean uploadFile(String pathname, String fileName, String originfilename) {
		boolean flag = false;
		InputStream inputStream = null;
		try {
			logger.info("开始上传文件--->>>--->>>");
			inputStream = new FileInputStream(new File(originfilename));
			initFtpClient();
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			CreateDirecroty(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
			logger.info("上传文件成功");
		} catch (Exception e) {
			logger.info("上传文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param pathname
	 *            ftp服务保存地址
	 * @param fileName
	 *            上传到ftp的文件名
	 * @param inputStream
	 *            输入文件流
	 * @return
	 */
	public boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
		boolean flag = false;
		try {
			logger.info("开始上传文件--->>>--->>>");
			initFtpClient();
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			CreateDirecroty(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
			logger.info("上传文件成功");
		} catch (Exception e) {
			logger.info("上传文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	// 改变目录路径
	public boolean changeWorkingDirectory(String directory) {
		boolean flag = true;
		try {
			flag = ftpClient.changeWorkingDirectory(directory);
			if (flag) {
				logger.info("进入文件夹" + directory + " 成功！");

			} else {
				logger.info("进入文件夹" + directory + " 失败！开始创建文件夹");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}

	// 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
	public boolean CreateDirecroty(String remote) throws IOException {
		boolean success = true;
		String directory = remote + "/";
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			String path = "";
			String paths = "";
			while (true) {
				String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
				path = path + "/" + subDirectory;
				if (!existFile(path)) {
					if (makeDirectory(subDirectory)) {
						changeWorkingDirectory(subDirectory);
					} else {
						logger.info("创建目录[" + subDirectory + "]失败");
						changeWorkingDirectory(subDirectory);
					}
				} else {
					changeWorkingDirectory(subDirectory);
				}

				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	// 判断ftp服务器文件是否存在
	public boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	// 创建目录
	public boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			flag = ftpClient.makeDirectory(dir);
			if (flag) {
				logger.info("创建文件夹" + dir + " 成功！");

			} else {
				logger.info("创建文件夹" + dir + " 失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * * 下载文件 *
	 * 
	 * @param pathname
	 *            FTP服务器文件目录 *
	 * @param filename
	 *            文件名称 *
	 * @param localpath
	 *            下载后的文件路径 *
	 * @return
	 */
	public boolean downloadFile(String pathname, String filename, String localpath) {
		boolean flag = false;
		OutputStream os = null;
		try {
			logger.info("开始下载文件");
			initFtpClient();
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
			logger.info("下载文件成功");
		} catch (Exception e) {
			logger.info("下载文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 批量下载 @Title: downloadFile2 @param pathname @param localpath @return:
	 * boolean @throws
	 */
	public boolean downloadFile2(String pathname, String localpath) {
		boolean flag = false;
		OutputStream os = null;
		try {
			logger.info("开始下载文件");
			initFtpClient();
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				logger.info("---->" + file.getName());
				File localFile = new File(localpath + "/" + file.getName());
				os = new FileOutputStream(localFile);
				ftpClient.retrieveFile(file.getName(), os);
				os.close();
			}
			ftpClient.logout();
			flag = true;
			logger.info("下载文件成功");
		} catch (Exception e) {
			logger.info("下载文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * * 删除文件 *
	 * 
	 * @param pathname
	 *            FTP服务器保存目录 *
	 * @param filename
	 *            要删除的文件名称 *
	 * @return
	 */
	public boolean deleteFile(String pathname, String filename) {
		boolean flag = false;
		try {
			logger.info("开始删除文件");
			initFtpClient();
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.dele(filename);
			ftpClient.logout();
			flag = true;
			logger.info("删除文件成功");
		} catch (Exception e) {
			logger.info("删除文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/*
	 * 读取文件
	 */
	public static String read(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) {
			return "File not found!";
		}
		FileInputStream fs;
		String result = null;
		try {
			fs = new FileInputStream(f);
			byte[] b = new byte[fs.available()];
			fs.read(b);
			fs.close();
			result = new String(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * 写文件
	 */
	public static boolean write(String fileName, String fileContent) {
		boolean result = false;
		File f = new File(fileName);
		try {
			FileOutputStream fs = new FileOutputStream(f);
			byte[] b = fileContent.getBytes();
			fs.write(b);
			fs.flush();
			fs.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 追加内容到文件
	 */
	public static boolean append(String fileName, String fileContent) {
		boolean result = false;
		File f = new File(fileName);
		try {
			if (f.exists()) {
				FileInputStream fsIn = new FileInputStream(f);
				byte[] bIn = new byte[fsIn.available()];
				fsIn.read(bIn);
				String oldFileContent = new String(bIn);
				// System.out.println("旧内容:" + oldFileContent);
				fsIn.close();
				if (!oldFileContent.equalsIgnoreCase("")) {
					fileContent = oldFileContent + "\r\n" + fileContent;
					// System.out.println("新内容:" + fileContent);
				}
			}

			FileOutputStream fs = new FileOutputStream(f);
			byte[] b = fileContent.getBytes();
			fs.write(b);
			fs.flush();
			fs.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		FtpUtils ftp = new FtpUtils();
		// ftp.uploadFile("/", "yu.txt", "D://1mm//zwj.doc");
		// ftp.downloadFile("/", "yu.txt", "F://ftpDownload//");
		//ftp.downloadFile2("/", "F://ftpDownload//");
		// ftp.deleteFile("ftpFile/data", "123.docx");
		// ftp.deleteFile("/", "yu.txt");
		logger.info(""+write("D://1mm//zwj.doc", "heihei."));
		logger.info(read("D://1mm//zwj.doc"));
		logger.info("ok");
	}
}