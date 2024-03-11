package cloud.spike.azure.storage;

import com.azure.storage.file.datalake.*;

public class AzureDataLakeGen2App {

    public static void main(String[] args) {
        // Azure Data Lake Storage Gen2 details
        String accountName = "sparknlp2641242170"; //"sparknlptestlakegen2";
        String accountKey = "flyMCQH505WnL/rTpw8blQuxBwQNEa1/d8Erettf6sHxwkAHvvJaJr2bE5J8SB7nTWeLbt4sFYNR2LiGo20sIg=="; //"OQMivAQi8283thqYoWKFBX0YQgkBc9ps2ecv6GQFL5SpxBu4JaMXCYitj+l2SH6G6XhHglkwhr1X+AStDUYznQ==";
        String fileSystemName = "test"; // Name of the file system
        String directoryName = "notebooks"; // Name of the directory
        String fileName = "Load_Model_from_Azure_Storage.ipynb"; // Name of the file you want to upload

        String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", accountName, accountKey); // Your ADLS Gen2 connection string

        // Create a DataLakeServiceClient using the connection string
        DataLakeServiceClient serviceClient = new DataLakeServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Create a DataLakeFileClient for the target file in the specified directory
        DataLakeFileClient fileClient = serviceClient
                .getFileSystemClient(fileSystemName)
                .getDirectoryClient(directoryName)
                .getFileClient(fileName);

        // Create a DataLakeFileSystemClient
//        DataLakeFileSystemClient fileSystemClient = new DataLakeFileSystemClientBuilder()
//                .endpoint("https://" + accountName + ".dfs.core.windows.net")
//                .fileSystemName(fileSystemName)
//                //.sasToken("<your_sas_token>")
//                .buildClient();

        // Create a DataLakeFileClient for the target file
//        DataLakeFileClient fileClient = fileSystemClient.getDirectoryClient(directoryName).getFileClient(fileName);

        // Specify the local path to the file you want to upload
        String localFilePath = "/home/danilo/Downloads/Load_Model_from_Azure_Storage.ipynb";

        // Upload the file to ADLS Gen2
        fileClient.uploadFromFile(localFilePath, true);
        System.out.println("File uploaded successfully.");
    }

}
