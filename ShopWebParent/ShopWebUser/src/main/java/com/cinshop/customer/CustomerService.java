package com.cinshop.customer;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private AddressRepository AddrRepo;

	public Customer save(Customer cust) {
		return custRepo.save(cust);
	}

	public void update(Integer custId, String newPassword) {
		custRepo.update(custId, newPassword);
	}

	public Optional<Customer> findCustomerByEmail(String email) {
		return custRepo.findCustomerByEmail(email);
	}

	public Customer saveGuest(Customer guest) {
		Long checkExistAcc = custRepo.countByEmail(guest.getEmail());
		if (checkExistAcc > 0) {
			Customer existAcc = custRepo.findCustomerByEmail(guest.getEmail()).get();
			//既に会員登録したユーザーを確認
			if (existAcc.getEnable()) {
				guest.setEnable(true);
				guest.setPassword(existAcc.getPassword());
				guest.setRole(existAcc.getRole());
			}

			// DBに情報を更新するために、フォームから受け取ったインスタンスをDBにある顧客IDを設定する
			guest.setId(existAcc.getId());

		} else {
			guest.setRole("ROLE_GUEST");
			// ゲストに仮パスワードを設定する
			guest.setPassword(Calendar.getInstance().getTimeInMillis() + "");
		}
		return custRepo.save(guest);
	}

	public Address saveAddress(Address address) {
		return AddrRepo.save(address);
	}

	public Optional<Address> findAddressById(Integer custId) {
		return AddrRepo.findAddressById(custId);

	}
}
