import axios from 'axios';


const axiosInstance = axios.create({});

//全局拦截器  拦截除了登录接口的所有请求，并在请求头中添加token
axiosInstance.interceptors.request.use(function (config) {

        if (!config.url.includes('/login')) {
            var Token = localStorage.getItem('token');
            config.headers['Token'] = Token;
        }
        return config;
    }, error => {
        return Promise.reject(error);
    }
);


axiosInstance.interceptors.response.use(response => {
        return response;
    },
    error => {
        if (error.response.status === 401) {
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
