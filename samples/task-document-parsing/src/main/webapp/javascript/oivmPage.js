setTimeout(addListener, 2000);

function addListener(){
  if(mstrApp) {
    if(mstrApp.docModelData.oid === "[your-guid-here]")  {
      var button = document.querySelectorAll('[k="[your-key-here]"]')[0];
      if(button) {
        button.addEventListener("click", doSomething)
      }
    }
  }
}

function functionCallback(data) {
  console.log("the function returned.");
  console.log(data);
}

function errorCallback() {
  console.log("An error occurred during task execution.");
}

function doSomething(){
  var callbacks = {
    success : functionCallback,
    failure : errorCallback
  };
  var params = {
    taskId : "customTask",
    sessionState : mstrApp.sessionState,
    msgID : mstrApp.getMsgID(),
    exportReturnParam : "export"
  };
  mstrmojo.xhr.request("POST", "http://localhost:8080/MicroStrategy/servlet/taskProc", callbacks, params);
}
