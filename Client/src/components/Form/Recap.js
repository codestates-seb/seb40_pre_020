// eslint-disable-next-line import/no-named-as-default
import ReCAPTCHA from 'react-google-recaptcha';

function Recap() {
  const onChange = (value) => {
    console.log('Captcha value:', value);
  };

  return (
    <div className="Recap">
      <ReCAPTCHA
        sitekey="6LeAKskiAAAAAEakbkKCcu_A-Lm5p0okXMuBBvPJ"
        onChange={onChange}
      />
    </div>
  );
}
export default Recap;
