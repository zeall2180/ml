// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {

    // Declare a mapping to store customer balances
    mapping(address => uint256) private balances;

    // Event to log deposits
    event Deposit(address indexed customer, uint256 amount);

    // Event to log withdrawals
    event Withdrawal(address indexed customer, uint256 amount);

    // Modifier to ensure sufficient balance for withdrawal
    modifier hasSufficientBalance(uint256 amount) {
        require(balances[msg.sender] >= amount, "Insufficient balance");
        _;
    }

    // Function to deposit money to the account
    function deposit() public payable {
        require(msg.value > 0, "Deposit amount must be greater than zero");
        balances[msg.sender] += msg.value;  // Increase balance by the amount sent
        emit Deposit(msg.sender, msg.value); // Emit deposit event
    }

    // Function to withdraw money from the account with Reentrancy Protection
    function withdraw(uint256 amount) public hasSufficientBalance(amount) {
        // First, update the balance
        balances[msg.sender] -= amount;  // Decrease balance by the withdrawal amount

        // Then, transfer the funds
        payable(msg.sender).transfer(amount);  // Transfer money to the customer

        // Emit withdrawal event
        emit Withdrawal(msg.sender, amount); 
    }

    // Function to check the balance of the customer
    function checkBalance() public view returns (uint256) {
        return balances[msg.sender];  // Return the balance of the calling customer
    }
}
