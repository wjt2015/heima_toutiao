{

fastdfs,

export FASTDFS_ALL_HOME=/Users/jintao9/linux2014/install_dir/fastdfs_all
/Users/jintao9/linux2014/wjt_projs/data/fastdfs_data
/Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf
10.222.100.10
http://10.222.100.10:8055/home.json 

fdfs_test /Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf download  group1 M00/00/00/Ct5kCmAH0aCAQhdaARhvkss0y3I963_big.pdf

fdfs_test /Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf getmeta   group1 M00/00/00/Ct5kCmAH0aCAQhdaARhvkss0y3I963_big.pdf

WITH_HTTPD
g_http_params
g_bind_addr
fdfs_global.h

fastcommon/common_define.h

fdfs_define.h 

fdfs_storaged.c:45:10: fatal error: 'storage_httpd.h' file not found
#include "storage_httpd.h"


}


