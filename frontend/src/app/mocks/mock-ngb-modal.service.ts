export class MockNgbModal {
    open(content: any, options?: any) {
      return {
        result: new Promise(() => {})
      };
    }
  }
  