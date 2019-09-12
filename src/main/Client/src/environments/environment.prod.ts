export const environment = {
  production: true,
  getBaseURL: function () {
          // console.log('getBaseURL -> production', this.production);
          return 'http://10.1.1.239:8083';

  }
};
