// Get a reference to the database service
var database = firebase.database();

function writeUserData(form) {
 var playersRef = firebase.database().ref("Employee/"+form.job.value+"/id"+form.phoneNumber.value);
playersRef.set ({
   Fname: form.firstName.value,
   Lname: form.lastName.value,
   Phone: form.phoneNumber.value,
   Gender: form.gender.value,
   Address: form.address.value,
   DOB: form.dob.value,
   JobTitle: form.job.value,
   Pin: form.pin.value,
   HireDate: form.hr.value

});

}
function logout(){
  firebase.auth().signOut();
}