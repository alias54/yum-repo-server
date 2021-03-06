package com.mongodb.gridfs;

import com.mongodb.DBObject;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Math.min;


public final class GridFSUtil {
  private GridFSUtil() {
  }

  public static void writeTo(GridFSDBFile dbFile, OutputStream outputStream, long length) throws IOException {
    long bytesWritten = 0;
    int chunkIndex = 0;
    byte[] buffer;
    int writeLength;
    while (bytesWritten < length) {
      buffer = dbFile.getChunk(chunkIndex);
      writeLength = (int) min(buffer.length, length - bytesWritten);
      outputStream.write(buffer, 0, writeLength);
      bytesWritten += (int) (length - bytesWritten);
      chunkIndex++;
    }
  }

  public static void remove(GridFSDBFile dbFile) {
    dbFile.remove();
  }

  public static void mergeMetaData(final GridFSFile gridFsFile, final DBObject metaDataToMerge) {
    final DBObject existingMetaData = gridFsFile.getMetaData();
    if (existingMetaData == null) {
      gridFsFile.setMetaData(metaDataToMerge);
    } else {
      existingMetaData.putAll(metaDataToMerge);
    }
  }
}
