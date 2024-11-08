// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {

    // Define a structure to store student data
    struct Student {
        uint256 id;
        string name;
        uint256 age;
        string course;
    }

    // Array to hold all students
    Student[] public students;

    // Event to log student creation
    event StudentCreated(uint256 id, string name, uint256 age, string course);

    // Function to add a student
    function addStudent(uint256 _id, string memory _name, uint256 _age, string memory _course) public {
        // Create a new student and add it to the array
        students.push(Student({
            id: _id,
            name: _name,
            age: _age,
            course: _course
        }));

        // Emit the event after adding the student
        emit StudentCreated(_id, _name, _age, _course);
    }

    // Function to get the student data by index
    function getStudent(uint256 index) public view returns (uint256, string memory, uint256, string memory) {
        require(index < students.length, "Student does not exist");

        Student memory student = students[index];
        return (student.id, student.name, student.age, student.course);
    }

    // Fallback function to handle unsupported function calls or Ether transfers
    fallback() external payable {
        revert("Fallback: This function does not exist.");
    }

    // Receive function to handle direct transfers of Ether
    receive() external payable {
        // Just receive Ether and do nothing with it
    }
}
